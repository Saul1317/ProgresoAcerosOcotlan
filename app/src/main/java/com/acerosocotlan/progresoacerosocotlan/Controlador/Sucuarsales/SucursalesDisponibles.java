package com.acerosocotlan.progresoacerosocotlan.Controlador.Sucuarsales;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.acerosocotlan.progresoacerosocotlan.Adaptador.AdapterRecyclerViewSucursales;
import com.acerosocotlan.progresoacerosocotlan.R;

import java.util.ArrayList;
import java.util.List;

public class SucursalesDisponibles extends AppCompatActivity {

    Sucursales sucursales;
    RecyclerView recyclerview_sucursales_disponibles;
    List<Sucursales> sucursalesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucursales_disponibles);
        //Se inician los componentes y se cargan las sucursales en el Recycler View
        iniciador();

    }

    private void iniciador() {
        recyclerview_sucursales_disponibles = (RecyclerView) findViewById(R.id.recyclerview_sucursales_disponibles);

        //Se inicializa el recycler view
        LinearLayoutManager l = new LinearLayoutManager(getApplicationContext());
        l.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview_sucursales_disponibles.setLayoutManager(l);
        //cargarSucursales() regresa la lista de todas las sucursales con su dirección
        AdapterRecyclerViewSucursales arv = new AdapterRecyclerViewSucursales(cargarSucursales(), R.layout.cardview_sucursal, SucursalesDisponibles.this, getApplicationContext());
        recyclerview_sucursales_disponibles.setAdapter(arv);
    }

    /*
    * Se crea una lista de sucursales
    * En caso de que la lista deje de estar estatica entonces simplemente se hace una peticion al web service para que regrese una lista de tipo sucursales
    */
    private List<Sucursales> cargarSucursales(){
        sucursalesList = new ArrayList<>();
        sucursales = new Sucursales("Jalisco", "Acatlán", "Acatlán, Jalisco Carr. Guadalajara-Barra de Navidad Km. 30 S/N");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Ajijic", "Ajijic, Jalisco. Carretera Poniente No.125 Col. Rancho del Oro. C.P. 45920");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Arandas", "Arandas, Jalisco. Madero No. 268 Col. Centro. C.P.47180");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Altos Arandas", "Blvd. Anacleto Glez. Flores #632 Col. Españita Tepatitlán de Morelos, Jalisco.");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Altos Matriz", "Los Altos, Arandas, Jalisco. Carretera Arandas-Tepatitlán Km. 10.05 Sin Colonia. C.P. 47180");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Atotonico", "Atotonilco el Alto, Jalisco. Carr. Atotonilco-Guadalajara Km. 1.5 Col. San Felipe. C.P. 47750");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Autlán", "Autlán de Navarro, Jalisco. Mariano Matamoros No. 533 Col. Centro. C.P. 48900");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Ayotlán", "Ayotlán, Jalisco. González Gallo No. 179 Col. Centro.");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Nayarit", "Bucerías", "Bucerías. Bahía de Banderas, Nayarit. Héroes de Nacozari No. 115 Col. Buenos Aires. C.P. 63732");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Cd. Guzmán", "Cd. Guzmán, Zapotlán el Grande, Jalisco. Av. Alberto Cárdenas Jimenez No. 237 Col. Centro. C.P. 49000");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Chapala", "Chapala, Jalisco. Carr. San Nicolás No. 36 Col. Las Redes Chapala. C.P. 45900");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Guanajuato", "Celaya", "SAABSA Aceros, Celaya, Guanajuato. Carr. Celaya-Salamanca Km 4.7 Col. 2da. Fracción. C.P. 38110");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Cocula", "Cocula, Jalisco. Carretera GDL-Barra de Navidad Km. 67 Col. Centro. C.P. 48500");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Degollado", "Degollado, Jalisco. Morelos No. 290 Col. Centro. C.P. 47980");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "El Salto", "El Salto, Jalisco. Carr. Guadalajara-El Salto Vía el Verde No. 68 Col. El Castillo. C.P. 45685");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Baja California", "Ensenada", "Ensenada, Baja California. Carr. Transpeninsular No. 6 Col. Carlos Pacheco. C.P.22890");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Guadalajara Bugambilias", "Zapopan, Jalisco. De los azahares No. 119, ezquina López Mateos sur Col. Cd. Bugambilias C.P. 45237");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Guadalajara Col. Jalisco", "Colonia Jalisco, Tonala, Jalisco. Perifèrico Oriente. No. 77 Col. Oblatos. C.P. 45403");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Guadalajara Ferrocarril", "Calle 9 num 461 Colonia Ferrocarril Cp: 44440");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Guadalajara Matriz", "Guadalajara, Jalisco. Av. Lázaro Cárdenas No. 2257 Col. Las Torres. C.P. 44920");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Guadalajara Periferico", "Carretera Morelia, Zapopan, Jalisco Carretera a Morelia No. 2050 Col. Francisco Sarabia. C.P. 45235");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Ixtapa", "Ixtapa CEDI, Puerto Vallarta, Jalisco. Carr. Las Juntas-Ixtapa No. 421 Col. Las Juntas. C.P. 48291");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Nayarit", "Ixtlán", "Ixtlán del Río, Nayarit. Hidalgo Pte. No. 614 Col. Moderna. C.P.63940");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Baja California", "Jardin Dorado", "Jardín Dorado, Baja California. Blvd. Terán-Terán No. 2713 Fracc. Jardín Dorado. C.P. 22200");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Jesús María", "Jesús María, Jalisco. Av. Jesús María No. 215 Col. Los Ladrillos. C.P. 47950");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "La Barca", "La Barca, Jalisco. Av. Lázaro Cárdenas No. 726 Col. Centro. C.P. 47910");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Michoacán", "Lázaro Cárdenas", "Lázaro Cárdenas, Michoacán. Carr. La Orilla Guacamayas Km. 1.5 Col. Aeropuerto. C.P. 60990");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Lagos de Morenos", "Lagos de Moreno, Jalisco. Francisco I. Madero No. 880 Col. Centro. C.P. 47400");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Michoacán", "La piedad", "La Piedad, Michoacán. Blvd. Lázaro Cárdenas No. 801 Col. Peña. C.P. 59389");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Baja California", "Mexicali", "Mexicali, Baja California. Blvd. Lázaro Cárdenas No. 1118 Col. Lázaro Cárdenas C.P. 21370");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Michoacán", "Morelia", "Morelia, Michoacán. Paseo de la República No.3595 Col. Rincón Quinceo. C.P. 58149");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Nuevo León", "Nuevo León", "Carretera Libre Federal Monterrey a Nuevo Laredo 29.5 Km. No. Exterior 29.5 Colonia Real del Sol C.P. 65555 Cienega de Flores, N.L.");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Ocotlán Caosa", "CAOSA, Ocotlán, Jalisco. Av. 20 de Noviembre No. 220 Col. Marcos Castellanos. C.P. 47870");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Ocotlán Ferretería", "Ferretería, Matriz Ocotlán, Jalisco. Francisco I. Madero No. 783-A Col. Florida. C.P. 47820");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Ocotlán Madero", "Madero, Ocotlán, Jalisco. Francisco I. Madero No. 785. Col. Florida. C.P. 47820");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Ocotlán Zula", "Zula -Matriz- Ocotlán, Jalisco. Francisco Zarco No. 1194 Col. Linda Vista. C.P. 47810.");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Pitillal", "Pitillal, Puerto Vallarta, Jalisco. Revolución No. 146 Col. Centro. Delegación Pitillal.");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Poncitlán", "Poncitlán, Jalisco. Prolongación Cuauhtemoc No. 499 Col. Toril. C.P. 45950");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Puerto Vallarta", "Puerto Vallarta, Jalisco. Carr. A Tepic No. 5378. Crucero Las Juntas. C.P. 48291");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Michoacán", "Sahuayo", "Sahuayo, Michoacán. Blvd. Lázaro Cárdenas Nte. No. 400 Col. Centro. C.P. 59000");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "San Francisco", "San Francisco de Asis, Jalisco. Carretera del Valle No. 356. Sin Colonia. C.P. 47755");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "San Miguel", "El 14, San Miguel El Alto, Jalisco. Carretera al Valle No. 54-A Col. Piedras Negras. C.P. 47140");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Tateposco", "Carretera libre a Zapotlanejo # 40 Col. Los puestos, Tlaquepaque Jalisco");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Tepatitlán", "Tepatitlán de Morelos, Jalisco. Blvd. Anacleto González #778 Col. Centro. C.P. 47600");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Nayarit", "Tepic", "Tepic, Nayarit. Av. Tecnológico No. 3980 Col. Cd. Puente San Cayetano. C.P. 63175");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Tateposco Segundas", "Blvd. Anacleto Glez Flores #545 C.P. 47698 Col. Los Rosales Tepatitlán de Morelos, Jalisco.");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Tesistán", "Av. Base Aérea Militar #370 Col. Jardines de Nuevo México, Zapopan Jalisco");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Baja California", "Tijuana", "Tijuana, Baja California. Carr. Antigua-Tecate No. 20 Col. Zermeño Los Pinos. C.P. 22000");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Tonalá", "Av. Tonalá #1420 Col. Cd. Aztlán, Tonalá Jalisco");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Jalisco", "Zapotlanejo", "Prol. Hidalgo #492 Col. Las Granjas, Zapotlanejo Jalisco");
        sucursalesList.add(sucursales);
        sucursales = new Sucursales("Baja California", "5 y 10", "5 y 10, Tijuana, Baja California. Blvd. Lázaro Cárdenas No. 888-B Fracc. Moreno La Mesa. C.P. 21105");
        sucursalesList.add(sucursales);
        return sucursalesList;
    }
}
