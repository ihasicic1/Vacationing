package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.MyException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Abstract class that implements core DAO CRUD methods for every entity
 * @author Ilhan Hasicic
 */
public abstract class AbstractDao <Type extends Idable> implements Dao<Type> {
    private Connection connection;
    private String tableName;

    public AbstractDao(String tableName){
        try {
            FileReader reader = new FileReader("db.properties");
            this.tableName = tableName;
            Properties p = new Properties();
            p.load(reader);
            this.connection = DriverManager.getConnection(p.getProperty("url"), p.getProperty("username"), p.getProperty("password"));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

    }

    public Connection getConnection(){ return this.connection; }
    public void setConnection(Connection connection){
        this.connection = connection;
    }

    public abstract Type row2object(ResultSet rs) throws MyException;

    public abstract Map<String, Object> object2row(Type object);

    public Type getById(int id){
        return null;
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

}
