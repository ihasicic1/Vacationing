package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

}
