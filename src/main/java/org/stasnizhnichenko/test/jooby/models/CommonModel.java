package org.stasnizhnichenko.test.jooby.models;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.sql2o.Sql2o;


public abstract class CommonModel {
    // data source
    protected static Sql2o sql2o = null;
    
    // конструктор #1
    protected CommonModel() {
    }

    // инициализация
    public static final void initialize(final DataSource ds) throws SQLException {
        try {
            Connection con = ds.getConnection();
            if (con == null) {
                throw new SQLException();
            	
            }
            CommonModel.sql2o = new Sql2o(ds);
        } catch (Exception exc) {
            throw new SQLException();
        	
        }
    }

    // имя таблицы в БД
    public static String tableName() throws SQLException {
        throw new SQLException();
    }

    // подсчет количества записей в БД
    public static int count() throws SQLException {
        throw new SQLException();
    }

    // проверка существования в БД записи с заданным идентификатором
    public static boolean exists(final int id) throws SQLException {
        throw new SQLException();
    }

    // чтение записи из БД по заданному идентификатору
    public static CommonModel find(final int id) throws SQLException {
        throw new SQLException();
    }

    // сохранение данной записи в БД
    public void save() throws SQLException {
        throw new SQLException();
    }

}
