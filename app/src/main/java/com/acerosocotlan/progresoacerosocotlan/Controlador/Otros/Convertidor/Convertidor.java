package com.acerosocotlan.progresoacerosocotlan.Controlador.Otros.Convertidor;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.acerosocotlan.progresoacerosocotlan.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Convertidor extends AppCompatActivity {

    AppCompatSpinner spinnerTipo, spinnerCantidad, spinnerResultado;
    TextInputEditText cantidad, resultado;
    int Tipo;
    final List<IconosUnidades> items = new ArrayList<IconosUnidades>(6);
    Asignacion_Formulas Asignar_formulas = new Asignacion_Formulas();
    ImageView imagen_fondo_convertidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertidor);
        imagen_fondo_convertidor = (ImageView) findViewById(R.id.imagen_fondo_convertidor);
        //Picasso.with(this).load("https://acerosocotlan.mx/app/AO_Fondo_APP.png").placeholder(R.drawable.ao__fondomesa_de_trabajo_5).into(imagen_fondo_convertidor);
        spinnerTipo = (AppCompatSpinner) findViewById(R.id.spinner_tipo);
        spinnerCantidad = (AppCompatSpinner) findViewById(R.id.spinner_de);
        spinnerResultado = (AppCompatSpinner) findViewById(R.id.spinner_a);
        Tipo = getIntent().getIntExtra("tipo", 0);
        List<IconosUnidades> item = GetLista(Tipo);
        cantidad = (TextInputEditText) findViewById(R.id.cantidad);
        resultado = (TextInputEditText) findViewById(R.id.resultado);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.unidadesMedida,
                R.layout.support_simple_spinner_dropdown_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(new Iconos_Unidades_SpinnerAdapter(this,item));
        spinnerTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                llenarAdapter(position, Tipo);
                cantidad.setText("");
                resultado.setText("");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cantidad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                double cant = 0;
                String ar = "";
                String tipo1 = "",tipo2 = "";
                try{
                    String t1 = ((TextView) spinnerCantidad.findViewById(R.id.texto)).getText().toString();
                    String t2 = ((TextView) spinnerResultado.findViewById(R.id.texto)).getText().toString();
                    ar = ((TextView)spinnerTipo.findViewById(R.id.texto)).getText().toString();
                    tipo1 = t1;
                    tipo2 = t2;
                    cant = Double.valueOf(cantidad.getText().toString());
                }
                catch(Exception msg)
                {}


                Log.d("VAR TIPO ", "Categoria: "+ar);
                if(ar.equals("Peso"))
                {
                    Log.d("VAR TIPO ", "contenido: "+tipo1+ " y "+tipo2);
                    Asignar_formulas.AsignacionFormulasPeso(resultado,tipo1, tipo2,  cant);
                }
                if(ar.equals("Longitud"))
                {
                    Asignar_formulas.AsignacionFormulasLongitud(resultado, tipo1, tipo2, cant);
                }
                if(ar.equals("Temperatura"))
                {
                    Asignar_formulas.AsignacionFormulasTemperatura(resultado, tipo1, tipo2, cant);
                }
                if(ar.equals("Área"))
                {
                    Asignar_formulas.AsignacionFormulasArea(resultado, tipo1, tipo2, cant);
                }
                if(ar.equals("Presión"))
                {
                    Asignar_formulas.AsignacionFormulasPresion(resultado, tipo1, tipo2, cant);
                }
                if(ar.equals("Calor y temperatura"))
                {
                    Asignar_formulas.AsignacionFormulasCalorEnergia(resultado, tipo1, tipo2, cant);
                }
                if(ar.equals("Volumen"))
                {
                    Asignar_formulas.AsignacionFormulasVolumen(resultado, tipo1, tipo2, cant);
                }
                if(ar.equals("Potencia"))
                {
                    Asignar_formulas.AsignacionFormulasPotencia(resultado, tipo1, tipo2, cant);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        spinnerCantidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                double cant = 0;
                String ar = "";
                String tipo1 = "",tipo2 = "";
                try{
                    String t1 = ((TextView) spinnerCantidad.findViewById(R.id.texto)).getText().toString();
                    String t2 = ((TextView) spinnerResultado.findViewById(R.id.texto)).getText().toString();
                    ar = ((TextView)spinnerTipo.findViewById(R.id.texto)).getText().toString();
                    tipo1 = t1;
                    tipo2 = t2;
                    cant = Double.valueOf(cantidad.getText().toString());
                }
                catch(Exception msg)
                {}


                Log.d("VAR TIPO ", "Categoria: "+ar);
                if(ar.equals("Peso"))
                {
                    Log.d("VAR TIPO ", "contenido: "+tipo1+ " y "+tipo2);
                    Asignar_formulas.AsignacionFormulasPeso(resultado,tipo1, tipo2,  cant);
                }
                if(ar.equals("Longitud"))
                {
                    Asignar_formulas.AsignacionFormulasLongitud(resultado, tipo1, tipo2, cant);
                }
                if(ar.equals("Temperatura"))
                {
                    Asignar_formulas.AsignacionFormulasTemperatura(resultado, tipo1, tipo2, cant);
                }
                if(ar.equals("Área"))
                {
                    Asignar_formulas.AsignacionFormulasArea(resultado, tipo1, tipo2, cant);
                }
                if(ar.equals("Presión"))
                {
                    Asignar_formulas.AsignacionFormulasPresion(resultado, tipo1, tipo2, cant);
                }
                if(ar.equals("Calor y temperatura"))
                {
                    Asignar_formulas.AsignacionFormulasCalorEnergia(resultado, tipo1, tipo2, cant);
                }
                if(ar.equals("Volumen"))
                {
                    Asignar_formulas.AsignacionFormulasVolumen(resultado, tipo1, tipo2, cant);
                }
                if(ar.equals("Potencia"))
                {
                    Asignar_formulas.AsignacionFormulasPotencia(resultado, tipo1, tipo2, cant);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerResultado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                double cant = 0;
                String ar = "";
                String tipo1 = "",tipo2 = "";
                try{
                    String t1 = ((TextView) spinnerCantidad.findViewById(R.id.texto)).getText().toString();
                    String t2 = ((TextView) spinnerResultado.findViewById(R.id.texto)).getText().toString();
                    ar = ((TextView)spinnerTipo.findViewById(R.id.texto)).getText().toString();
                    tipo1 = t1;
                    tipo2 = t2;
                    cant = Double.valueOf(cantidad.getText().toString());
                }
                catch(Exception msg)
                {}


                Log.d("VAR TIPO ", "Categoria: "+ar);
                if(ar.equals("Peso"))
                {
                    Log.d("VAR TIPO ", "contenido: "+tipo1+ " y "+tipo2);
                    Asignar_formulas.AsignacionFormulasPeso(resultado,tipo1, tipo2,  cant);
                }
                if(ar.equals("Longitud"))
                {
                    Asignar_formulas.AsignacionFormulasLongitud(resultado, tipo1, tipo2, cant);
                }
                if(ar.equals("Temperatura"))
                {
                    Asignar_formulas.AsignacionFormulasTemperatura(resultado, tipo1, tipo2, cant);
                }
                if(ar.equals("Área"))
                {
                    Asignar_formulas.AsignacionFormulasArea(resultado, tipo1, tipo2, cant);
                }
                if(ar.equals("Presión"))
                {
                    Asignar_formulas.AsignacionFormulasPresion(resultado, tipo1, tipo2, cant);
                }
                if(ar.equals("Calor y temperatura"))
                {
                    Asignar_formulas.AsignacionFormulasCalorEnergia(resultado, tipo1, tipo2, cant);
                }
                if(ar.equals("Volumen"))
                {
                    Asignar_formulas.AsignacionFormulasVolumen(resultado, tipo1, tipo2, cant);
                }
                if(ar.equals("Potencia"))
                {
                    Asignar_formulas.AsignacionFormulasPotencia(resultado, tipo1, tipo2, cant);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public List<IconosUnidades> GetLista(int tipo) {
        if(tipo == 0) {
            items.add(new IconosUnidades(getString(R.string.peso), R.drawable.c_peso));
            items.add(new IconosUnidades(getString(R.string.longitud), R.drawable.c_longitud));
            items.add(new IconosUnidades(getString(R.string.area), R.drawable.c_area));
            items.add(new IconosUnidades(getString(R.string.temperatura), R.drawable.c_temperatura));
            items.add(new IconosUnidades(getString(R.string.presion), R.drawable.c_presion));
            items.add(new IconosUnidades(getString(R.string.caloryenergia), R.drawable.c_caloryenergia));
            items.add(new IconosUnidades(getString(R.string.potencia), R.drawable.c_potencia));
            items.add(new IconosUnidades(getString(R.string.volumen), R.drawable.c_volumen));
        }
        if(tipo == 1) {
            items.add(new IconosUnidades(getString(R.string.longitud), R.drawable.c_longitud));
            items.add(new IconosUnidades(getString(R.string.peso), R.drawable.c_peso));
            items.add(new IconosUnidades(getString(R.string.area), R.drawable.c_area));
            items.add(new IconosUnidades(getString(R.string.temperatura), R.drawable.c_temperatura));
            items.add(new IconosUnidades(getString(R.string.presion), R.drawable.c_presion));
            items.add(new IconosUnidades(getString(R.string.caloryenergia), R.drawable.c_caloryenergia));
            items.add(new IconosUnidades(getString(R.string.potencia), R.drawable.c_potencia));
            items.add(new IconosUnidades(getString(R.string.volumen), R.drawable.c_volumen));
        }
        if(tipo == 2) {
            items.add(new IconosUnidades(getString(R.string.area), R.drawable.c_area));
            items.add(new IconosUnidades(getString(R.string.peso), R.drawable.c_peso));
            items.add(new IconosUnidades(getString(R.string.longitud), R.drawable.c_longitud));
            items.add(new IconosUnidades(getString(R.string.temperatura), R.drawable.c_temperatura));
            items.add(new IconosUnidades(getString(R.string.presion), R.drawable.c_presion));
            items.add(new IconosUnidades(getString(R.string.caloryenergia), R.drawable.c_caloryenergia));
            items.add(new IconosUnidades(getString(R.string.potencia), R.drawable.c_potencia));
            items.add(new IconosUnidades(getString(R.string.volumen), R.drawable.c_volumen));
        }
        if(tipo == 3) {
            items.add(new IconosUnidades(getString(R.string.temperatura), R.drawable.c_temperatura));
            items.add(new IconosUnidades(getString(R.string.peso), R.drawable.c_peso));
            items.add(new IconosUnidades(getString(R.string.longitud), R.drawable.c_longitud));
            items.add(new IconosUnidades(getString(R.string.area), R.drawable.c_area));
            items.add(new IconosUnidades(getString(R.string.presion), R.drawable.c_presion));
            items.add(new IconosUnidades(getString(R.string.caloryenergia), R.drawable.c_caloryenergia));
            items.add(new IconosUnidades(getString(R.string.potencia), R.drawable.c_potencia));
            items.add(new IconosUnidades(getString(R.string.volumen), R.drawable.c_volumen));
        }
        if(tipo == 4) {
            items.add(new IconosUnidades(getString(R.string.caloryenergia), R.drawable.c_caloryenergia));
            items.add(new IconosUnidades(getString(R.string.peso), R.drawable.c_peso));
            items.add(new IconosUnidades(getString(R.string.longitud), R.drawable.c_longitud));
            items.add(new IconosUnidades(getString(R.string.area), R.drawable.c_area));
            items.add(new IconosUnidades(getString(R.string.temperatura), R.drawable.c_temperatura));
            items.add(new IconosUnidades(getString(R.string.presion), R.drawable.c_presion));
            items.add(new IconosUnidades(getString(R.string.potencia), R.drawable.c_potencia));
            items.add(new IconosUnidades(getString(R.string.volumen), R.drawable.c_volumen));
        }
        if(tipo == 5) {
            items.add(new IconosUnidades(getString(R.string.potencia), R.drawable.c_potencia));
            items.add(new IconosUnidades(getString(R.string.peso), R.drawable.c_peso));
            items.add(new IconosUnidades(getString(R.string.longitud), R.drawable.c_longitud));
            items.add(new IconosUnidades(getString(R.string.area), R.drawable.c_area));
            items.add(new IconosUnidades(getString(R.string.temperatura), R.drawable.c_temperatura));
            items.add(new IconosUnidades(getString(R.string.presion), R.drawable.c_presion));
            items.add(new IconosUnidades(getString(R.string.caloryenergia), R.drawable.c_caloryenergia));
            items.add(new IconosUnidades(getString(R.string.volumen), R.drawable.c_volumen));
        }
        if(tipo == 6) {
            items.add(new IconosUnidades(getString(R.string.presion), R.drawable.c_presion));
            items.add(new IconosUnidades(getString(R.string.peso), R.drawable.c_peso));
            items.add(new IconosUnidades(getString(R.string.longitud), R.drawable.c_longitud));
            items.add(new IconosUnidades(getString(R.string.area), R.drawable.c_area));
            items.add(new IconosUnidades(getString(R.string.temperatura), R.drawable.c_temperatura));
            items.add(new IconosUnidades(getString(R.string.caloryenergia), R.drawable.c_caloryenergia));
            items.add(new IconosUnidades(getString(R.string.potencia), R.drawable.c_potencia));
            items.add(new IconosUnidades(getString(R.string.volumen), R.drawable.c_volumen));
        }
        if(tipo == 7) {
            items.add(new IconosUnidades(getString(R.string.volumen), R.drawable.c_volumen));
            items.add(new IconosUnidades(getString(R.string.peso), R.drawable.c_peso));
            items.add(new IconosUnidades(getString(R.string.longitud), R.drawable.c_longitud));
            items.add(new IconosUnidades(getString(R.string.area), R.drawable.c_area));
            items.add(new IconosUnidades(getString(R.string.temperatura), R.drawable.c_temperatura));
            items.add(new IconosUnidades(getString(R.string.presion), R.drawable.c_presion));
            items.add(new IconosUnidades(getString(R.string.caloryenergia), R.drawable.c_caloryenergia));
            items.add(new IconosUnidades(getString(R.string.potencia), R.drawable.c_potencia));
        }
        return items;
    }

    public void llenarAdapter(int Position, int tipo) {
        if (tipo == 0) {
            switch (Position) {
                case 0:

                    List<Categorias> listaP = new ArrayList();
                    listaP.add(new Categorias(getString(R.string.kgmetro)));
                    listaP.add(new Categorias(getString(R.string.lbp)));
                    listaP.add(new Categorias(getString(R.string.lb)));
                    listaP.add(new Categorias(getString(R.string.kg)));
                    listaP.add(new Categorias(getString(R.string.gr)));
                    listaP.add(new Categorias(getString(R.string.tonelada)));
                    listaP.add(new Categorias(getString(R.string.oz)));

                    List<Categorias> listaK = new ArrayList();
                    listaK.add(new Categorias(getString(R.string.lbp)));
                    listaK.add(new Categorias(getString(R.string.kgmetro)));
                    listaK.add(new Categorias(getString(R.string.lb)));
                    listaK.add(new Categorias(getString(R.string.kg)));
                    listaK.add(new Categorias(getString(R.string.gr)));
                    listaK.add(new Categorias(getString(R.string.tonelada)));
                    listaK.add(new Categorias(getString(R.string.oz)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaP));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaK));
                    break;
                case 1:
                    List<Categorias> listaL = new ArrayList();
                    listaL.add(new Categorias(getString(R.string.in)));
                    listaL.add(new Categorias(getString(R.string.m)));
                    listaL.add(new Categorias(getString(R.string.cm)));
                    listaL.add(new Categorias(getString(R.string.mm)));
                    listaL.add(new Categorias(getString(R.string.km)));
                    listaL.add(new Categorias(getString(R.string.ft)));
                    listaL.add(new Categorias(getString(R.string.milla)));
                    listaL.add(new Categorias(getString(R.string.yd)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaL));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaL));
                    break;
                case 2:
                    List<Categorias> listaA = new ArrayList();
                    listaA.add(new Categorias(getString(R.string.cm2)));
                    listaA.add(new Categorias(getString(R.string.m2)));
                    listaA.add(new Categorias(getString(R.string.km2)));
                    listaA.add(new Categorias(getString(R.string.ha)));
                    listaA.add(new Categorias(getString(R.string.in2)));
                    listaA.add(new Categorias(getString(R.string.ft2)));
                    listaA.add(new Categorias(getString(R.string.yd2)));
                    listaA.add(new Categorias(getString(R.string.milla2)));
                    listaA.add(new Categorias(getString(R.string.acre2)));
                    listaA.add(new Categorias(getString(R.string.mm2)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaA));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaA));
                    break;
                case 3:
                    List<Categorias> listaT = new ArrayList();
                    listaT.add(new Categorias(getString(R.string.F)));
                    listaT.add(new Categorias(getString(R.string.C)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaT));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaT));
                    break;
                case 4:
                    List<Categorias> listaC = new ArrayList();
                    listaC.add(new Categorias(getString(R.string.Joule)));
                    listaC.add(new Categorias(getString(R.string.kcal)));
                    listaC.add(new Categorias(getString(R.string.kgm)));
                    listaC.add(new Categorias(getString(R.string.kwhr)));
                    listaC.add(new Categorias(getString(R.string.cal)));
                    listaC.add(new Categorias(getString(R.string.hphr)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaC));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaC));
                    break;
                case 5:
                    List<Categorias> listaPre = new ArrayList();
                    listaPre.add(new Categorias(getString(R.string.kgcm2)));
                    listaPre.add(new Categorias(getString(R.string.kgfmm2)));
                    listaPre.add(new Categorias(getString(R.string.mmHG)));
                    listaPre.add(new Categorias(getString(R.string.Mpa)));
                    listaPre.add(new Categorias(getString(R.string.Nmm2)));
                    listaPre.add(new Categorias(getString(R.string.psi)));
                    listaPre.add(new Categorias(getString(R.string.Atm)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaPre));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaPre));
                    break;
                case 6:
                    List<Categorias> listaPo = new ArrayList();
                    listaPo.add(new Categorias(getString(R.string.kw)));
                    listaPo.add(new Categorias(getString(R.string.hp)));
                    listaPo.add(new Categorias(getString(R.string.btuhr)));
                    listaPo.add(new Categorias(getString(R.string.watts)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaPo));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaPo));
                    break;
                case 7:
                    List<Categorias> listaV = new ArrayList();
                    listaV.add(new Categorias(getString(R.string.m3)));
                    listaV.add(new Categorias(getString(R.string.lt)));
                    listaV.add(new Categorias(getString(R.string.in3)));
                    listaV.add(new Categorias(getString(R.string.ft3)));
                    listaV.add(new Categorias(getString(R.string.yd3)));
                    listaV.add(new Categorias(getString(R.string.gal)));
                    listaV.add(new Categorias(getString(R.string.cm3)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaV));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaV));
                    break;
            }
        }
        if (tipo == 1) {
            switch (Position) {
                case 0:
                    List<Categorias> listaL = new ArrayList();
                    listaL.add(new Categorias(getString(R.string.in)));
                    listaL.add(new Categorias(getString(R.string.m)));
                    listaL.add(new Categorias(getString(R.string.cm)));
                    listaL.add(new Categorias(getString(R.string.mm)));
                    listaL.add(new Categorias(getString(R.string.km)));
                    listaL.add(new Categorias(getString(R.string.ft)));
                    listaL.add(new Categorias(getString(R.string.milla)));
                    listaL.add(new Categorias(getString(R.string.yd)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaL));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaL));
                    break;
                case 1:

                    List<Categorias> lista = new ArrayList();
                    lista.add(new Categorias(getString(R.string.lb)));
                    lista.add(new Categorias(getString(R.string.kg)));
                    lista.add(new Categorias(getString(R.string.gr)));
                    lista.add(new Categorias(getString(R.string.tonelada)));
                    lista.add(new Categorias(getString(R.string.oz)));

                   /* final ArrayAdapter<CharSequence> unidadP = ArrayAdapter.createFromResource(getApplicationContext(),
                            R.array.unidadesPeso,
                            R.layout.support_simple_spinner_dropdown_item);
                    unidadP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);*/

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, lista));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, lista));
                case 2:
                    List<Categorias> listaA = new ArrayList();
                    listaA.add(new Categorias(getString(R.string.cm2)));
                    listaA.add(new Categorias(getString(R.string.m2)));
                    listaA.add(new Categorias(getString(R.string.km2)));
                    listaA.add(new Categorias(getString(R.string.ha)));
                    listaA.add(new Categorias(getString(R.string.in2)));
                    listaA.add(new Categorias(getString(R.string.ft2)));
                    listaA.add(new Categorias(getString(R.string.yd2)));
                    listaA.add(new Categorias(getString(R.string.milla2)));
                    listaA.add(new Categorias(getString(R.string.acre2)));
                    listaA.add(new Categorias(getString(R.string.mm2)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaA));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaA));
                case 3:
                    List<Categorias> listaT = new ArrayList();
                    listaT.add(new Categorias(getString(R.string.F)));
                    listaT.add(new Categorias(getString(R.string.C)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaT));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaT));
                    break;
                case 4:
                    List<Categorias> listaPre = new ArrayList();
                    listaPre.add(new Categorias(getString(R.string.kgcm2)));
                    listaPre.add(new Categorias(getString(R.string.kgfmm2)));
                    listaPre.add(new Categorias(getString(R.string.mmHG)));
                    listaPre.add(new Categorias(getString(R.string.Mpa)));
                    listaPre.add(new Categorias(getString(R.string.Nmm2)));
                    listaPre.add(new Categorias(getString(R.string.psi)));
                    listaPre.add(new Categorias(getString(R.string.Atm)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaPre));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaPre));
                    break;
                case 5:
                    List<Categorias> listaC = new ArrayList();
                    listaC.add(new Categorias(getString(R.string.Joule)));
                    listaC.add(new Categorias(getString(R.string.kcal)));
                    listaC.add(new Categorias(getString(R.string.kgm)));
                    listaC.add(new Categorias(getString(R.string.kwhr)));
                    listaC.add(new Categorias(getString(R.string.cal)));
                    listaC.add(new Categorias(getString(R.string.hphr)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaC));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaC));
                    break;
                case 6:
                    List<Categorias> listaPo = new ArrayList();
                    listaPo.add(new Categorias(getString(R.string.kw)));
                    listaPo.add(new Categorias(getString(R.string.hp)));
                    listaPo.add(new Categorias(getString(R.string.btuhr)));
                    listaPo.add(new Categorias(getString(R.string.watts)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaPo));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaPo));
                    break;
                case 7:
                    List<Categorias> listaV = new ArrayList();
                    listaV.add(new Categorias(getString(R.string.m3)));
                    listaV.add(new Categorias(getString(R.string.lt)));
                    listaV.add(new Categorias(getString(R.string.in3)));
                    listaV.add(new Categorias(getString(R.string.ft3)));
                    listaV.add(new Categorias(getString(R.string.yd3)));
                    listaV.add(new Categorias(getString(R.string.gal)));
                    listaV.add(new Categorias(getString(R.string.cm3)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaV));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaV));
                    break;
            }
        }
        if (tipo == 2) {
            switch (Position) {
                case 0:
                    List<Categorias> listaA = new ArrayList();
                    listaA.add(new Categorias(getString(R.string.cm2)));
                    listaA.add(new Categorias(getString(R.string.m2)));
                    listaA.add(new Categorias(getString(R.string.km2)));
                    listaA.add(new Categorias(getString(R.string.ha)));
                    listaA.add(new Categorias(getString(R.string.in2)));
                    listaA.add(new Categorias(getString(R.string.ft2)));
                    listaA.add(new Categorias(getString(R.string.yd2)));
                    listaA.add(new Categorias(getString(R.string.milla2)));
                    listaA.add(new Categorias(getString(R.string.acre2)));
                    listaA.add(new Categorias(getString(R.string.mm2)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaA));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaA));
                    break;
                case 1:
                    List<Categorias> listaP = new ArrayList();
                    listaP.add(new Categorias(getString(R.string.lb)));
                    listaP.add(new Categorias(getString(R.string.kg)));
                    listaP.add(new Categorias(getString(R.string.gr)));
                    listaP.add(new Categorias(getString(R.string.tonelada)));
                    listaP.add(new Categorias(getString(R.string.oz)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaP));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaP));
                    break;
                case 2:
                    List<Categorias> listaL = new ArrayList();
                    listaL.add(new Categorias(getString(R.string.in)));
                    listaL.add(new Categorias(getString(R.string.m)));
                    listaL.add(new Categorias(getString(R.string.cm)));
                    listaL.add(new Categorias(getString(R.string.mm)));
                    listaL.add(new Categorias(getString(R.string.km)));
                    listaL.add(new Categorias(getString(R.string.ft)));
                    listaL.add(new Categorias(getString(R.string.milla)));
                    listaL.add(new Categorias(getString(R.string.yd)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaL));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaL));
                    break;
                case 3:
                    List<Categorias> listaT = new ArrayList();
                    listaT.add(new Categorias(getString(R.string.F)));
                    listaT.add(new Categorias(getString(R.string.C)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaT));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaT));
                    break;
                case 4:
                    List<Categorias> listaPre = new ArrayList();
                    listaPre.add(new Categorias(getString(R.string.kgcm2)));
                    listaPre.add(new Categorias(getString(R.string.kgfmm2)));
                    listaPre.add(new Categorias(getString(R.string.mmHG)));
                    listaPre.add(new Categorias(getString(R.string.Mpa)));
                    listaPre.add(new Categorias(getString(R.string.Nmm2)));
                    listaPre.add(new Categorias(getString(R.string.psi)));
                    listaPre.add(new Categorias(getString(R.string.Atm)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaPre));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaPre));
                    break;
                case 5:
                    List<Categorias> listaC = new ArrayList();
                    listaC.add(new Categorias(getString(R.string.Joule)));
                    listaC.add(new Categorias(getString(R.string.kcal)));
                    listaC.add(new Categorias(getString(R.string.kgm)));
                    listaC.add(new Categorias(getString(R.string.kwhr)));
                    listaC.add(new Categorias(getString(R.string.cal)));
                    listaC.add(new Categorias(getString(R.string.hphr)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaC));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaC));
                    break;
                case 6:
                    List<Categorias> listaPo = new ArrayList();
                    listaPo.add(new Categorias(getString(R.string.kw)));
                    listaPo.add(new Categorias(getString(R.string.hp)));
                    listaPo.add(new Categorias(getString(R.string.btuhr)));
                    listaPo.add(new Categorias(getString(R.string.watts)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaPo));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaPo));
                    break;
                case 7:
                    List<Categorias> listaV = new ArrayList();
                    listaV.add(new Categorias(getString(R.string.m3)));
                    listaV.add(new Categorias(getString(R.string.lt)));
                    listaV.add(new Categorias(getString(R.string.in3)));
                    listaV.add(new Categorias(getString(R.string.ft3)));
                    listaV.add(new Categorias(getString(R.string.yd3)));
                    listaV.add(new Categorias(getString(R.string.gal)));
                    listaV.add(new Categorias(getString(R.string.cm3)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaV));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaV));
                    break;
            }
        }
        if (tipo == 3) {
            switch (Position) {
                case 0:
                    List<Categorias> listaT = new ArrayList();
                    listaT.add(new Categorias(getString(R.string.F)));
                    listaT.add(new Categorias(getString(R.string.C)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaT));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaT));
                    break;
                case 1:
                    List<Categorias> listaP = new ArrayList();
                    listaP.add(new Categorias(getString(R.string.lb)));
                    listaP.add(new Categorias(getString(R.string.kg)));
                    listaP.add(new Categorias(getString(R.string.gr)));
                    listaP.add(new Categorias(getString(R.string.tonelada)));
                    listaP.add(new Categorias(getString(R.string.oz)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaP));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaP));
                    break;
                case 2:
                    List<Categorias> listaL = new ArrayList();
                    listaL.add(new Categorias(getString(R.string.in)));
                    listaL.add(new Categorias(getString(R.string.m)));
                    listaL.add(new Categorias(getString(R.string.cm)));
                    listaL.add(new Categorias(getString(R.string.mm)));
                    listaL.add(new Categorias(getString(R.string.km)));
                    listaL.add(new Categorias(getString(R.string.ft)));
                    listaL.add(new Categorias(getString(R.string.milla)));
                    listaL.add(new Categorias(getString(R.string.yd)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaL));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaL));
                    break;
                case 3:
                    List<Categorias> listaA = new ArrayList();
                    listaA.add(new Categorias(getString(R.string.cm2)));
                    listaA.add(new Categorias(getString(R.string.m2)));
                    listaA.add(new Categorias(getString(R.string.km2)));
                    listaA.add(new Categorias(getString(R.string.ha)));
                    listaA.add(new Categorias(getString(R.string.in2)));
                    listaA.add(new Categorias(getString(R.string.ft2)));
                    listaA.add(new Categorias(getString(R.string.yd2)));
                    listaA.add(new Categorias(getString(R.string.milla2)));
                    listaA.add(new Categorias(getString(R.string.acre2)));
                    listaA.add(new Categorias(getString(R.string.mm2)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaA));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaA));
                    break;
                case 4:
                    List<Categorias> listaPre = new ArrayList();
                    listaPre.add(new Categorias(getString(R.string.kgcm2)));
                    listaPre.add(new Categorias(getString(R.string.kgfmm2)));
                    listaPre.add(new Categorias(getString(R.string.mmHG)));
                    listaPre.add(new Categorias(getString(R.string.Mpa)));
                    listaPre.add(new Categorias(getString(R.string.Nmm2)));
                    listaPre.add(new Categorias(getString(R.string.psi)));
                    listaPre.add(new Categorias(getString(R.string.Atm)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaPre));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaPre));
                    break;
                case 5:
                    List<Categorias> listaC = new ArrayList();
                    listaC.add(new Categorias(getString(R.string.Joule)));
                    listaC.add(new Categorias(getString(R.string.kcal)));
                    listaC.add(new Categorias(getString(R.string.kgm)));
                    listaC.add(new Categorias(getString(R.string.kwhr)));
                    listaC.add(new Categorias(getString(R.string.cal)));
                    listaC.add(new Categorias(getString(R.string.hphr)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaC));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaC));
                    break;
                case 6:
                    List<Categorias> listaPo = new ArrayList();
                    listaPo.add(new Categorias(getString(R.string.kw)));
                    listaPo.add(new Categorias(getString(R.string.hp)));
                    listaPo.add(new Categorias(getString(R.string.btuhr)));
                    listaPo.add(new Categorias(getString(R.string.watts)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaPo));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaPo));
                    break;
                case 7:
                    List<Categorias> listaV = new ArrayList();
                    listaV.add(new Categorias(getString(R.string.m3)));
                    listaV.add(new Categorias(getString(R.string.lt)));
                    listaV.add(new Categorias(getString(R.string.in3)));
                    listaV.add(new Categorias(getString(R.string.ft3)));
                    listaV.add(new Categorias(getString(R.string.yd3)));
                    listaV.add(new Categorias(getString(R.string.gal)));
                    listaV.add(new Categorias(getString(R.string.cm3)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaV));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaV));
                    break;
            }
        }
        if (tipo == 4) {
            switch (Position) {
                case 0:
                    List<Categorias> listaC = new ArrayList();
                    listaC.add(new Categorias(getString(R.string.Joule)));
                    listaC.add(new Categorias(getString(R.string.kcal)));
                    listaC.add(new Categorias(getString(R.string.kgm)));
                    listaC.add(new Categorias(getString(R.string.kwhr)));
                    listaC.add(new Categorias(getString(R.string.cal)));
                    listaC.add(new Categorias(getString(R.string.hphr)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaC));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaC));
                    break;
                case 1:
                    List<Categorias> listaP = new ArrayList();
                    listaP.add(new Categorias(getString(R.string.lb)));
                    listaP.add(new Categorias(getString(R.string.kg)));
                    listaP.add(new Categorias(getString(R.string.gr)));
                    listaP.add(new Categorias(getString(R.string.tonelada)));
                    listaP.add(new Categorias(getString(R.string.oz)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaP));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaP));
                    break;
                case 2:
                    List<Categorias> listaL = new ArrayList();
                    listaL.add(new Categorias(getString(R.string.in)));
                    listaL.add(new Categorias(getString(R.string.m)));
                    listaL.add(new Categorias(getString(R.string.cm)));
                    listaL.add(new Categorias(getString(R.string.mm)));
                    listaL.add(new Categorias(getString(R.string.km)));
                    listaL.add(new Categorias(getString(R.string.ft)));
                    listaL.add(new Categorias(getString(R.string.milla)));
                    listaL.add(new Categorias(getString(R.string.yd)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaL));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaL));
                    break;
                case 3:
                    List<Categorias> listaA = new ArrayList();
                    listaA.add(new Categorias(getString(R.string.cm2)));
                    listaA.add(new Categorias(getString(R.string.m2)));
                    listaA.add(new Categorias(getString(R.string.km2)));
                    listaA.add(new Categorias(getString(R.string.ha)));
                    listaA.add(new Categorias(getString(R.string.in2)));
                    listaA.add(new Categorias(getString(R.string.ft2)));
                    listaA.add(new Categorias(getString(R.string.yd2)));
                    listaA.add(new Categorias(getString(R.string.milla2)));
                    listaA.add(new Categorias(getString(R.string.acre2)));
                    listaA.add(new Categorias(getString(R.string.mm2)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaA));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaA));
                    break;
                case 4:
                    List<Categorias> listaT = new ArrayList();
                    listaT.add(new Categorias(getString(R.string.F)));
                    listaT.add(new Categorias(getString(R.string.C)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaT));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaT));
                    break;
                case 5:
                    List<Categorias> listaPre = new ArrayList();
                    listaPre.add(new Categorias(getString(R.string.kgcm2)));
                    listaPre.add(new Categorias(getString(R.string.kgfmm2)));
                    listaPre.add(new Categorias(getString(R.string.mmHG)));
                    listaPre.add(new Categorias(getString(R.string.Mpa)));
                    listaPre.add(new Categorias(getString(R.string.Nmm2)));
                    listaPre.add(new Categorias(getString(R.string.psi)));
                    listaPre.add(new Categorias(getString(R.string.Atm)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaPre));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaPre));
                    break;
                case 6:
                    List<Categorias> listaPo = new ArrayList();
                    listaPo.add(new Categorias(getString(R.string.kw)));
                    listaPo.add(new Categorias(getString(R.string.hp)));
                    listaPo.add(new Categorias(getString(R.string.btuhr)));
                    listaPo.add(new Categorias(getString(R.string.watts)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaPo));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaPo));
                    break;
                case 7:
                    List<Categorias> listaV = new ArrayList();
                    listaV.add(new Categorias(getString(R.string.m3)));
                    listaV.add(new Categorias(getString(R.string.lt)));
                    listaV.add(new Categorias(getString(R.string.in3)));
                    listaV.add(new Categorias(getString(R.string.ft3)));
                    listaV.add(new Categorias(getString(R.string.yd3)));
                    listaV.add(new Categorias(getString(R.string.gal)));
                    listaV.add(new Categorias(getString(R.string.cm3)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaV));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaV));
                    break;
            }
        }
        if (tipo == 5) {
            switch (Position) {
                case 0:
                    List<Categorias> listaPo = new ArrayList();
                    listaPo.add(new Categorias(getString(R.string.kw)));
                    listaPo.add(new Categorias(getString(R.string.hp)));
                    listaPo.add(new Categorias(getString(R.string.btuhr)));
                    listaPo.add(new Categorias(getString(R.string.watts)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaPo));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaPo));
                    break;
                case 1:
                    List<Categorias> listaP = new ArrayList();
                    listaP.add(new Categorias(getString(R.string.lb)));
                    listaP.add(new Categorias(getString(R.string.kg)));
                    listaP.add(new Categorias(getString(R.string.gr)));
                    listaP.add(new Categorias(getString(R.string.tonelada)));
                    listaP.add(new Categorias(getString(R.string.oz)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaP));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaP));
                    break;
                case 2:
                    List<Categorias> listaL = new ArrayList();
                    listaL.add(new Categorias(getString(R.string.in)));
                    listaL.add(new Categorias(getString(R.string.m)));
                    listaL.add(new Categorias(getString(R.string.cm)));
                    listaL.add(new Categorias(getString(R.string.mm)));
                    listaL.add(new Categorias(getString(R.string.km)));
                    listaL.add(new Categorias(getString(R.string.ft)));
                    listaL.add(new Categorias(getString(R.string.milla)));
                    listaL.add(new Categorias(getString(R.string.yd)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaL));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaL));
                    break;
                case 3:
                    List<Categorias> listaA = new ArrayList();
                    listaA.add(new Categorias(getString(R.string.cm2)));
                    listaA.add(new Categorias(getString(R.string.m2)));
                    listaA.add(new Categorias(getString(R.string.km2)));
                    listaA.add(new Categorias(getString(R.string.ha)));
                    listaA.add(new Categorias(getString(R.string.in2)));
                    listaA.add(new Categorias(getString(R.string.ft2)));
                    listaA.add(new Categorias(getString(R.string.yd2)));
                    listaA.add(new Categorias(getString(R.string.milla2)));
                    listaA.add(new Categorias(getString(R.string.acre2)));
                    listaA.add(new Categorias(getString(R.string.mm2)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaA));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaA));
                    break;
                case 4:
                    List<Categorias> listaT = new ArrayList();
                    listaT.add(new Categorias(getString(R.string.F)));
                    listaT.add(new Categorias(getString(R.string.C)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaT));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaT));
                    break;
                case 5:
                    List<Categorias> listaPre = new ArrayList();
                    listaPre.add(new Categorias(getString(R.string.kgcm2)));
                    listaPre.add(new Categorias(getString(R.string.kgfmm2)));
                    listaPre.add(new Categorias(getString(R.string.mmHG)));
                    listaPre.add(new Categorias(getString(R.string.Mpa)));
                    listaPre.add(new Categorias(getString(R.string.Nmm2)));
                    listaPre.add(new Categorias(getString(R.string.psi)));
                    listaPre.add(new Categorias(getString(R.string.Atm)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaPre));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaPre));
                    break;
                case 6:
                    List<Categorias> listaC = new ArrayList();
                    listaC.add(new Categorias(getString(R.string.Joule)));
                    listaC.add(new Categorias(getString(R.string.kcal)));
                    listaC.add(new Categorias(getString(R.string.kgm)));
                    listaC.add(new Categorias(getString(R.string.kwhr)));
                    listaC.add(new Categorias(getString(R.string.cal)));
                    listaC.add(new Categorias(getString(R.string.hphr)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaC));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaC));
                    break;
                case 7:
                    List<Categorias> listaV = new ArrayList();
                    listaV.add(new Categorias(getString(R.string.m3)));
                    listaV.add(new Categorias(getString(R.string.lt)));
                    listaV.add(new Categorias(getString(R.string.in3)));
                    listaV.add(new Categorias(getString(R.string.ft3)));
                    listaV.add(new Categorias(getString(R.string.yd3)));
                    listaV.add(new Categorias(getString(R.string.gal)));
                    listaV.add(new Categorias(getString(R.string.cm3)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaV));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaV));
                    break;
            }
        }
        if (tipo == 6) {
            switch (Position) {
                case 0:
                    List<Categorias> listaPre = new ArrayList();
                    listaPre.add(new Categorias(getString(R.string.kgcm2)));
                    listaPre.add(new Categorias(getString(R.string.kgfmm2)));
                    listaPre.add(new Categorias(getString(R.string.mmHG)));
                    listaPre.add(new Categorias(getString(R.string.Mpa)));
                    listaPre.add(new Categorias(getString(R.string.Nmm2)));
                    listaPre.add(new Categorias(getString(R.string.psi)));
                    listaPre.add(new Categorias(getString(R.string.Atm)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaPre));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaPre));
                    break;
                case 1:
                    List<Categorias> listaP = new ArrayList();
                    listaP.add(new Categorias(getString(R.string.lb)));
                    listaP.add(new Categorias(getString(R.string.kg)));
                    listaP.add(new Categorias(getString(R.string.gr)));
                    listaP.add(new Categorias(getString(R.string.tonelada)));
                    listaP.add(new Categorias(getString(R.string.oz)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaP));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaP));
                    break;
                case 2:
                    List<Categorias> listaL = new ArrayList();
                    listaL.add(new Categorias(getString(R.string.in)));
                    listaL.add(new Categorias(getString(R.string.m)));
                    listaL.add(new Categorias(getString(R.string.cm)));
                    listaL.add(new Categorias(getString(R.string.mm)));
                    listaL.add(new Categorias(getString(R.string.km)));
                    listaL.add(new Categorias(getString(R.string.ft)));
                    listaL.add(new Categorias(getString(R.string.milla)));
                    listaL.add(new Categorias(getString(R.string.yd)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaL));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaL));
                    break;
                case 3:
                    List<Categorias> listaA = new ArrayList();
                    listaA.add(new Categorias(getString(R.string.cm2)));
                    listaA.add(new Categorias(getString(R.string.m2)));
                    listaA.add(new Categorias(getString(R.string.km2)));
                    listaA.add(new Categorias(getString(R.string.ha)));
                    listaA.add(new Categorias(getString(R.string.in2)));
                    listaA.add(new Categorias(getString(R.string.ft2)));
                    listaA.add(new Categorias(getString(R.string.yd2)));
                    listaA.add(new Categorias(getString(R.string.milla2)));
                    listaA.add(new Categorias(getString(R.string.acre2)));
                    listaA.add(new Categorias(getString(R.string.mm2)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaA));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaA));
                    break;
                case 4:
                    List<Categorias> listaT = new ArrayList();
                    listaT.add(new Categorias(getString(R.string.F)));
                    listaT.add(new Categorias(getString(R.string.C)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaT));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaT));
                    break;
                case 5:
                    List<Categorias> listaC = new ArrayList();
                    listaC.add(new Categorias(getString(R.string.Joule)));
                    listaC.add(new Categorias(getString(R.string.kcal)));
                    listaC.add(new Categorias(getString(R.string.kgm)));
                    listaC.add(new Categorias(getString(R.string.kwhr)));
                    listaC.add(new Categorias(getString(R.string.cal)));
                    listaC.add(new Categorias(getString(R.string.hphr)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaC));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaC));
                    break;
                case 6:
                    List<Categorias> listaPo = new ArrayList();
                    listaPo.add(new Categorias(getString(R.string.kw)));
                    listaPo.add(new Categorias(getString(R.string.hp)));
                    listaPo.add(new Categorias(getString(R.string.btuhr)));
                    listaPo.add(new Categorias(getString(R.string.watts)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaPo));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaPo));
                    break;
                case 7:
                    List<Categorias> listaV = new ArrayList();
                    listaV.add(new Categorias(getString(R.string.m3)));
                    listaV.add(new Categorias(getString(R.string.lt)));
                    listaV.add(new Categorias(getString(R.string.in3)));
                    listaV.add(new Categorias(getString(R.string.ft3)));
                    listaV.add(new Categorias(getString(R.string.yd3)));
                    listaV.add(new Categorias(getString(R.string.gal)));
                    listaV.add(new Categorias(getString(R.string.cm3)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaV));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaV));
                    break;
            }
        }
        if (tipo == 7) {
            switch (Position) {
                case 0:
                    List<Categorias> listaV = new ArrayList();
                    listaV.add(new Categorias(getString(R.string.m3)));
                    listaV.add(new Categorias(getString(R.string.lt)));
                    listaV.add(new Categorias(getString(R.string.in3)));
                    listaV.add(new Categorias(getString(R.string.ft3)));
                    listaV.add(new Categorias(getString(R.string.yd3)));
                    listaV.add(new Categorias(getString(R.string.gal)));
                    listaV.add(new Categorias(getString(R.string.cm3)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaV));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaV));
                    break;
                case 1:
                    List<Categorias> listaP = new ArrayList();
                    listaP.add(new Categorias(getString(R.string.lb)));
                    listaP.add(new Categorias(getString(R.string.kg)));
                    listaP.add(new Categorias(getString(R.string.gr)));
                    listaP.add(new Categorias(getString(R.string.tonelada)));
                    listaP.add(new Categorias(getString(R.string.oz)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaP));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaP));
                    break;
                case 2:
                    List<Categorias> listaL = new ArrayList();
                    listaL.add(new Categorias(getString(R.string.in)));
                    listaL.add(new Categorias(getString(R.string.m)));
                    listaL.add(new Categorias(getString(R.string.cm)));
                    listaL.add(new Categorias(getString(R.string.mm)));
                    listaL.add(new Categorias(getString(R.string.km)));
                    listaL.add(new Categorias(getString(R.string.ft)));
                    listaL.add(new Categorias(getString(R.string.milla)));
                    listaL.add(new Categorias(getString(R.string.yd)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaL));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaL));
                    break;
                case 3:
                    List<Categorias> listaA = new ArrayList();
                    listaA.add(new Categorias(getString(R.string.cm2)));
                    listaA.add(new Categorias(getString(R.string.m2)));
                    listaA.add(new Categorias(getString(R.string.km2)));
                    listaA.add(new Categorias(getString(R.string.ha)));
                    listaA.add(new Categorias(getString(R.string.in2)));
                    listaA.add(new Categorias(getString(R.string.ft2)));
                    listaA.add(new Categorias(getString(R.string.yd2)));
                    listaA.add(new Categorias(getString(R.string.milla2)));
                    listaA.add(new Categorias(getString(R.string.acre2)));
                    listaA.add(new Categorias(getString(R.string.mm2)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaA));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaA));
                    break;
                case 4:
                    List<Categorias> listaT = new ArrayList();
                    listaT.add(new Categorias(getString(R.string.F)));
                    listaT.add(new Categorias(getString(R.string.C)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaT));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaT));
                    break;
                case 5:
                    List<Categorias> listaPre = new ArrayList();
                    listaPre.add(new Categorias(getString(R.string.kgcm2)));
                    listaPre.add(new Categorias(getString(R.string.kgfmm2)));
                    listaPre.add(new Categorias(getString(R.string.mmHG)));
                    listaPre.add(new Categorias(getString(R.string.Mpa)));
                    listaPre.add(new Categorias(getString(R.string.Nmm2)));
                    listaPre.add(new Categorias(getString(R.string.psi)));
                    listaPre.add(new Categorias(getString(R.string.Atm)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaPre));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaPre));
                    break;
                case 6:
                    List<Categorias> listaC = new ArrayList();
                    listaC.add(new Categorias(getString(R.string.Joule)));
                    listaC.add(new Categorias(getString(R.string.kcal)));
                    listaC.add(new Categorias(getString(R.string.kgm)));
                    listaC.add(new Categorias(getString(R.string.kwhr)));
                    listaC.add(new Categorias(getString(R.string.cal)));
                    listaC.add(new Categorias(getString(R.string.hphr)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaC));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaC));
                    break;
                case 7:
                    List<Categorias> listaPo = new ArrayList();
                    listaPo.add(new Categorias(getString(R.string.kw)));
                    listaPo.add(new Categorias(getString(R.string.hp)));
                    listaPo.add(new Categorias(getString(R.string.btuhr)));
                    listaPo.add(new Categorias(getString(R.string.watts)));

                    spinnerCantidad.setAdapter(new adapter_spinner_categorias(this, listaPo));
                    spinnerResultado.setAdapter(new adapter_spinner_categorias(this, listaPo));
                    break;

            }
        }
    }
}
