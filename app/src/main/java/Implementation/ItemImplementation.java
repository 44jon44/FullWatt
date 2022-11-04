package Implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import interfaces.ItemAVInterface;

public class ItemImplementation implements ItemAVInterface {
    //ATRIBUTOS DE LA CONEXION A BD
    private Connection con;
    private PreparedStatement stmt;

    /*CONDFIGURACION*/
    private String driver;
    private String url;
    private String user;
    private String passwd;
    private ResourceBundle configFile;

    //SENTENCIAS SQL
    private final String cargarModelo="SELECT * FROM item_attribute_value where item_attribute=339 GROUP BY value";
    private final String cargarTempCol="SELECT * FROM item_attribute_value where item_attribute=? GROUP BY value";
    private final String cargarTensionAC="SELECT * FROM item_attribute_value where item_attribute=202 GROUP BY value";
    private final String cargarTensionDC="SELECT * FROM item_attribute_value where item_attribute=203 GROUP BY value";
    private final String cargarLumenesM="SELECT * FROM item_attribute_value where item_attribute=264 GROUP BY value";
    private final String cargarLedsM="SELECT * FROM item_attribute_value where item_attribute=81 GROUP BY value";
    private final String cargarIp="SELECT * FROM item_attribute_value where item_attribute=90 GROUP BY value";

    /*CONEXION CON EL ARCHIVO DE CONFIGURACION*/
    public ItemImplementation() {
        this.configFile = ResourceBundle.getBundle("propeties.t");
        this.driver = this.configFile.getString("driver");
        this.url = this.configFile.getString("url");
        this.user = this.configFile.getString("user");
        this.passwd = this.configFile.getString("passwd");
    }

    //CONEXION CON LA BD
    public void openConnection() {
        try {

            //CONEXION XAMPP
            con = DriverManager.getConnection(this.url, this.user, this.passwd);



        } catch (SQLException e) {
            System.out.println("Error al intentar abrir la BD");
        }
    }

    //CERRAR LA CONEXION CON LA BD
    private void closeConnection() throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
        if(con != null) {
            con.close();
        }
    }


    @Override
    public ArrayList<String> cargarModeloValues() {

        ArrayList<String> modelos=new ArrayList<>();
        String modelo;

        ResultSet rs=null;

        this.openConnection();

        try {
            stmt=con.prepareStatement(cargarModelo);

            rs=stmt.executeQuery();

            while (rs.next()){
                modelo=new String();
                modelos.add(modelo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            this.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelos;
    }

    @Override
    public ArrayList<String> cargarTempColValues(int filter) {
        return null;
    }

    @Override
    public ArrayList<String> cargarTensionValues() {
        return null;
    }

    @Override
    public ArrayList<String> cargarPotenciaMValues() {
        return null;
    }

    @Override
    public ArrayList<String> cargarLumenesMValues() {
        return null;
    }

    @Override
    public ArrayList<String> cargarLedsMValues() {
        return null;
    }

    @Override
    public ArrayList<String> cargarIPValues() {
        return null;
    }
}
