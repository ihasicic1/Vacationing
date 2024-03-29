package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.MyException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * Abstract class that implements core DAO CRUD methods for every entity
 * @author Ilhan Hasicic
 */
public abstract class AbstractDao <Type extends Idable> implements Dao<Type> {
    private static Connection connection = null;
    private String tableName;

    public AbstractDao(String tableName){
        this.tableName = tableName;
        createConnection();
    }

    private static void createConnection(){
        if(connection == null){
            try{
                FileReader reader = new FileReader("db.properties");
                Properties p = new Properties();
                p.load(reader);
                connection = DriverManager.getConnection(p.getProperty("url"), p.getProperty("username"), p.getProperty("password"));
            }catch(SQLException | IOException e){
                System.out.println("Unable to connect to the database");
                e.printStackTrace();
            }
            finally {
                Runtime.getRuntime().addShutdownHook(new Thread(){
                    @Override
                    public void run(){
                        try {
                            connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    public Connection getConnection(){ return connection; }


    /**
     * Method for maping ResultSet into Object
     * @param rs - result set from database
     * @return a Bean object for specific table
     * @throws MyException in case of error with db
     */
    public abstract Type row2object(ResultSet rs) throws MyException;

    /**
     * Method for maping Object into Map
     * @param object - a bean object for specific table
     * @return key, value sorted map object
     */
    public abstract Map<String, Object> object2row(Type object);

    public Type getById(int id) throws MyException{
        return executeQueryUnique("SELECT * FROM "+this.tableName+" WHERE id = ?", new Object[]{id});
    }

    public List<Type> getAll() throws MyException{
        return executeQuery("SELECT * FROM "+ tableName, null);
    }

    public void delete(int id) throws MyException {
        String sql = "DELETE FROM "+tableName+" WHERE id = ?";
        try{
            PreparedStatement stmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new MyException(e.getMessage(), e);
        }
    }

    public Type add(Type item) throws MyException{
        Map<String, Object> row = object2row(item);
        Map.Entry<String, String> columns = prepareInsertParts(row);

        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(tableName);
        builder.append(" (").append(columns.getKey()).append(") ");
        builder.append("VALUES (").append(columns.getValue()).append(")");

        try{
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString(), Statement.RETURN_GENERATED_KEYS);
            // bind params. IMPORTANT treeMap is used to keep columns sorted so params are bind correctly
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) continue; // skip ID
                stmt.setObject(counter, entry.getValue());
                counter = counter + 1;
            }
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next(); // we know that there is one key
            item.setId(rs.getInt(1)); //set id to return it back */

            return item;
        }catch (SQLException e){
            throw new MyException(e.getMessage(), e);
        }
    }


    public Type update(Type item) throws MyException{
        Map<String, Object> row = object2row(item);
        String updateColumns = prepareUpdateParts(row);
        StringBuilder builder = new StringBuilder();
        builder.append("UPDATE ")
                .append(tableName)
                .append(" SET ")
                .append(updateColumns)
                .append(" WHERE id = ?");

        try{
            PreparedStatement stmt = getConnection().prepareStatement(builder.toString());
            int counter = 1;
            for (Map.Entry<String, Object> entry: row.entrySet()) {
                if (entry.getKey().equals("id")) continue; // skip ID
                stmt.setObject(counter, entry.getValue());
                counter = counter + 1;
            }
            stmt.setObject(counter, item.getId());
            stmt.executeUpdate();
            return item;
        }catch (SQLException e){
            throw new MyException(e.getMessage(), e);
        }
    }

    /**
     * Utility method for executing any kind of query
     * @param query - SQL query
     * @param params - params for query
     * @return List of objects from database
     * @throws MyException in case of error with database
     */
    public List<Type> executeQuery(String query, Object[] params) throws MyException {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            if(params != null){
                for(int i = 1; i <= params.length; i++){
                    stmt.setObject(i, params[i-1]);
                }
            }
            ResultSet rs = stmt.executeQuery();
            ArrayList<Type> resultList = new ArrayList<>();
            while(rs.next()){
                resultList.add(row2object(rs));
            }
            return resultList;
        } catch (SQLException e) {
            throw new MyException(e.getMessage(), e);
        }
    }

    /**
     * Utility for query execution that always returns single record
     * @param query - query that returns a single record
     * @param params - list of params for sql query
     * @return Object
     * @throws MyException in case when object is not found
     */
    public Type executeQueryUnique(String query, Object[] params) throws MyException {
        List<Type> result = executeQuery(query, params);
        if(result != null && result.size() == 1){
            return result.get(0);
        }
        else{
            throw new MyException("Object not found");
        }
    }

    /**
     * Accepts Key-Value storage of column names and returns CSV of columns and question marks for insert statement
     * Example: (id, name, city) ?, ?, ?
     */
    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();
        StringBuilder questions = new StringBuilder();
        int counter = 0;
        for(Map.Entry<String, Object> entry: row.entrySet()){
            counter = counter + 1;
            if(entry.getKey().equals("id")) continue; //skip insertion of id due to autoincrement
            columns.append(entry.getKey());
            questions.append("?");
            if(row.size() != counter){
                columns.append(",");
                questions.append(",");
            }
        }
        return new AbstractMap.SimpleEntry<>(columns.toString(), questions.toString());
    }

    /**
     * Prepare columns for update statement id=?, name=?, ...
     * @param row - row to be converted into string
     * @return String for update statement
     */
    private String prepareUpdateParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();
        int counter = 0;
        for(Map.Entry<String, Object> entry: row.entrySet()){
            counter = counter + 1;
            if(entry.getKey().equals("id")) continue; //skip update of id due to where clause
            columns.append(entry.getKey()).append("= ?");
            if(row.size() != counter){
                columns.append(",");
            }
        }
        return columns.toString();
    }

}
