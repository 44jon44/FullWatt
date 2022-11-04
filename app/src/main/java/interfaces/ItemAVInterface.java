package interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemAVInterface {
    public ArrayList<String> cargarModeloValues() throws SQLException;

    public ArrayList<String> cargarTempColValues(int filter);

    public ArrayList<String> cargarTensionValues();

    public ArrayList<String> cargarPotenciaMValues();

    public ArrayList<String> cargarLumenesMValues();

    public ArrayList<String> cargarLedsMValues();

    public ArrayList<String> cargarIPValues();

}
