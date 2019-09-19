package com.acerosocotlan.progresoacerosocotlan.Controlador.Otros.Convertidor;

import android.widget.EditText;

import java.text.DecimalFormat;

/**
 * Created by rvazquez on 16/05/2017.
 */
public class Asignacion_Formulas
{
    DecimalFormat formato = new DecimalFormat("0.00");
    DecimalFormat formatoEsp = new DecimalFormat("0.0");
    Formulas formulas = new Formulas();


    public void AsignacionFormulasPeso(EditText resultado, String tipo1, String tipo2, double cant)
    {
        //Log.d("Si entra a esta funcion", "PRUEBA DE FUNCION");
        switch (tipo1)
        {
            case "lb/p":
                if(tipo2.equals("lb/p"))
                {
                    resultado.setText(String.valueOf(cant));
                }
                if(tipo2.equals("kg/m"))
                {
                    double res = formulas.FormulasPeso(cant, tipo1, tipo2);
                    resultado.setText(formatoEsp.format(res));
                }
                if(tipo2.equals("lb"))
                {
                    double res = 0;
                    resultado.setText(formato.format(res));
                }
                if(tipo2.equals("kg"))
                {
                    double res = 0;
                    resultado.setText(formato.format(res));
                }
                if(tipo2.equals("gr"))
                {
                    double res = 0;
                    resultado.setText(formato.format(res));
                }
                if(tipo2.equals("tonelada"))
                {
                    double res = 0;
                    resultado.setText(formato.format(res));
                }
                if(tipo2.equals("oz"))
                {
                    double res = 0;
                    resultado.setText(formato.format(res));
                }
                break;
            case "kg/m":
                if(tipo2.equals("kg/m"))
                {
                    resultado.setText(String.valueOf(cant));
                }
                if(tipo2.equals("lb/p"))
                {
                    double res = formulas.FormulasPeso(cant, tipo1, tipo2);
                    resultado.setText(formatoEsp.format(res));
                }
                if(tipo2.equals("lb"))
                {
                    double res = 0;
                    resultado.setText(formato.format(res));
                }
                if(tipo2.equals("kg"))
                {
                    double res = 0;
                    resultado.setText(formato.format(res));
                }
                if(tipo2.equals("gr"))
                {
                    double res = 0;
                    resultado.setText(formato.format(res));
                }
                if(tipo2.equals("tonelada"))
                {
                    double res = 0;
                    resultado.setText(formato.format(res));
                }
                if(tipo2.equals("oz"))
                {
                    double res = 0;
                    resultado.setText(formato.format(res));
                }
                break;
            case "lb":
                if(tipo2.equals("lb"))
                {
                    resultado.setText(String.valueOf(cant));
                }
                if(tipo2.equals("kg"))
                {
                    double res = formulas.FormulasPeso(cant, tipo1, tipo2);
                    resultado.setText(formato.format(res));
                }
                if(tipo2.equals("gr"))
                {
                    double res = formulas.FormulasPeso(cant, tipo1, tipo2);
                    resultado.setText(formato.format(res));
                }
                if(tipo2.equals("tonelada"))
                {
                    double res = formulas.FormulasPeso(cant, tipo1, tipo2);
                    resultado.setText(formato.format(res));
                }
                if(tipo2.equals("oz"))
                {
                    double res = formulas.FormulasPeso(cant, tipo1, tipo2);
                    resultado.setText(formato.format(res));
                }
                break;
            case "kg":
                if(tipo2.equals("lb"))
                {
                    double res = formulas.FormulasPeso(cant, tipo1, tipo2);
                    resultado.setText(formato.format(res));
                }
                if(tipo2.equals("kg"))
                {
                    resultado.setText(String.valueOf(cant));
                }
                if(tipo2.equals("gr"))
                {
                    double res = formulas.FormulasPeso(cant, tipo1, tipo2);
                    resultado.setText(formato.format(res));
                }
                if(tipo2.equals("tonelada"))
                {
                    double res = formulas.FormulasPeso(cant, tipo1, tipo2);
                    resultado.setText(formato.format(res));
                }
                if(tipo2.equals("oz"))
                {
                    double res = formulas.FormulasPeso(cant, tipo1, tipo2);
                    resultado.setText(formato.format(res));
                }
                break;
            case "gr":
                if(tipo2.equals("lb"))
                {
                    double res = formulas.FormulasPeso(cant, tipo1, tipo2);
                    resultado.setText(formato.format(res));
                }
                if(tipo2.equals("kg"))
                {
                    double res = formulas.FormulasPeso(cant, tipo1, tipo2);
                    resultado.setText(formato.format(res));
                }
                if(tipo2.equals("gr"))
                {

                    resultado.setText(String.valueOf(cant));
                }
                if(tipo2.equals("tonelada"))
                {
                    double res = formulas.FormulasPeso(cant, tipo1, tipo2);
                    resultado.setText(formato.format(res));
                }
                if(tipo2.equals("oz"))
                {
                    double res = formulas.FormulasPeso(cant, tipo1, tipo2);
                    resultado.setText(formato.format(res));
                }
                break;
            case "tonelada":
                if(tipo2.equals("lb"))
                {
                    double res = formulas.FormulasPeso(cant, tipo1, tipo2);
                    resultado.setText(formato.format(res));
                }
                if(tipo2.equals("kg"))
                {
                    double res = formulas.FormulasPeso(cant, tipo1, tipo2);
                    resultado.setText(formato.format(res));
                }
                if(tipo2.equals("gr"))
                {
                    double res = formulas.FormulasPeso(cant, tipo1, tipo2);
                    resultado.setText(formato.format(res));
                }
                if(tipo2.equals("tonelada"))
                {
                    resultado.setText(String.valueOf(cant));
                }
                if(tipo2.equals("oz"))
                {
                    double res = formulas.FormulasPeso(cant, tipo1, tipo2);
                    resultado.setText(formato.format(res));
                }
                break;
            case "oz":
                if(tipo2.equals("lb"))
                {
                    double res = formulas.FormulasPeso(cant, tipo1, tipo2);
                    resultado.setText(formato.format(res));
                }
                if(tipo2.equals("kg"))
                {
                    double res = formulas.FormulasPeso(cant, tipo1, tipo2);
                    resultado.setText(formato.format(res));
                }
                if(tipo2.equals("gr"))
                {
                    double res = formulas.FormulasPeso(cant, tipo1, tipo2);
                    resultado.setText(formato.format(res));
                }
                if(tipo2.equals("tonelada"))
                {
                    double res = formulas.FormulasPeso(cant, tipo1, tipo2);
                    resultado.setText(formato.format(res));
                }
                if(tipo2.equals("oz"))
                {

                    resultado.setText(String.valueOf(cant));
                }
                break;

        }
    }

    public void AsignacionFormulasLongitud(EditText resultado, String tipo1, String tipo2, double cant)
    {
        double res = 0;
        switch (tipo1)
        {
            case "in":
                switch (tipo2)
                {
                    case "in":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "cm":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "mm":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "m":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "km":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "ft":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "milla":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "yd":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                }
                break;
            case "m":
                switch (tipo2)
                {
                    case "m":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "cm":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "mm":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "in":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "km":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "ft":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "milla":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "yd":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                }
                break;
            case "cm":
                switch (tipo2)
                {
                    case "cm":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "in":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "mm":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "m":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "km":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "ft":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "milla":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "yd":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));

                }
                break;
            case "mm":
                switch (tipo2)
                {
                    case "mm":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "cm":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "in":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "m":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "km":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "ft":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "milla":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "yd":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));

                }
                break;
            case "km":
                switch (tipo2)
                {
                    case "km":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "cm":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "mm":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "m":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "in":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "ft":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "milla":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "yd":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));

                }
                break;
            case "ft":
                switch (tipo2)
                {
                    case "ft":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "cm":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "mm":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "m":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "km":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "in":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "milla":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "yd":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));

                }
                break;
            case "milla":
                switch (tipo2)
                {
                    case "milla":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "cm":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "mm":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "m":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "km":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "ft":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "in":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "yd":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));

                }
                break;
            case "yd":
                switch (tipo2)
                {
                    case "yd":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "cm":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "mm":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "m":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "km":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "ft":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "milla":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "in":
                        res = formulas.FormulasLongitud(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                }
                break;
        }
    }

    public void AsignacionFormulasTemperatura(EditText resultado, String tipo1, String tipo2, double cant)
    {
        double res = 0;
        switch (tipo1) {
            case "°F":
                switch (tipo2) {
                    case "°F":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "°C":
                        res = formulas.FormulasTemperatura(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                }
                break;
            case "°C":
                switch (tipo2) {
                    case "°C":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "°F":
                        res = formulas.FormulasTemperatura(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                }
                break;
        }
    }
    public void AsignacionFormulasArea(EditText resultado, String tipo1, String tipo2, double cant)
    {
        double res = 0;
        switch (tipo1) {
            case "cm2":
                switch (tipo2) {
                    case "cm2":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "m2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "km2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "ha":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "in2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "ft2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "yd2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "milla2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "acre2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "mm2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                }
                break;
            case "m2":
                switch (tipo2) {
                    case "m2":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "cm2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "km2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "ha":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "in2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "ft2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "yd2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "milla2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "acre2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "mm2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                }
                break;
            case "km2":
                switch (tipo2) {
                    case "km2":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "cm2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "m2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "ha":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "in2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "ft2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "yd2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "milla2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "acre2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "mm2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                }
                break;
            case "ha":
                switch (tipo2) {
                    case "ha":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "cm2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "km2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "m2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "in2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "ft2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "yd2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "milla2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "acre2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "mm2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                }
                break;
            case "in2":
                switch (tipo2) {
                    case "in2":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "cm2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "km2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "ha":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "m2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "ft2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "yd2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "milla2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "acre2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "mm2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                }
                break;
            case "ft2":
                switch (tipo2) {
                    case "ft2":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "cm2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "km2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "ha":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "in2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "m2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "yd2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "milla2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "acre2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "mm2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                }
                break;
            case "yd2":
                switch (tipo2) {
                    case "yd2":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "cm2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "km2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "ha":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "in2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "ft2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "m2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "milla2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "acre2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "mm2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                }
                break;
            case "milla2":
                switch (tipo2) {
                    case "milla2":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "cm2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "km2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "ha":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "in2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "ft2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "yd2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "m2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "acre2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "mm2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                }
                break;
            case "acre2":
                switch (tipo2) {
                    case "acre2":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "cm2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "km2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "ha":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "in2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "ft2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "yd2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "milla2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "m2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "mm2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                }
                break;
            case "mm2":
                switch (tipo2) {
                    case "mm2":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "cm2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "km2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "ha":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "in2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "ft2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "yd2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "milla2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "acre2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "m2":
                        res = formulas.FormulasAreas(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                }
                break;
        }
    }

    public void AsignacionFormulasPresion(EditText resultado, String tipo1, String tipo2, double cant)
    {
        double res = 0;
        switch (tipo1) {
            case "kg/cm2":
                switch (tipo2) {
                    case "kg/cm2":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "kgf/mm2":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "mm HG":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "Mpa":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "N/mm2":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "psi":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "Atm":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                }
                break;
            case "kgf/mm2":
                switch (tipo2) {
                    case "kgf/mm2":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "kg/cm2":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "mm HG":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "Mpa":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "N/mm2":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "psi":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "Atm":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                }
                break;
            case "mm HG":
                switch (tipo2) {
                    case "mm HG":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "kgf/mm2":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "kg/cm2":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "Mpa":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "N/mm2":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "psi":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "Atm":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                }
                break;
            case "Mpa":
                switch (tipo2) {
                    case "Mpa":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "kgf/mm2":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "mm HG":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "kg/cm2":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "N/mm2":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "psi":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "Atm":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                }
                break;
            case "N/mm2":
                switch (tipo2) {
                    case "N/mm2":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "kgf/mm2":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "mm HG":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "Mpa":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "kg/cm2":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "psi":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "Atm":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                }
                break;
            case "psi":
                switch (tipo2) {
                    case "psi":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "kgf/mm2":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "mm HG":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "Mpa":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "N/mm2":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "kg/cm2":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "Atm":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                }
                break;
            case "Atm":
                switch (tipo2) {
                    case "Atm":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "kgf/mm2":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "mm HG":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "Mpa":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "N/mm2":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "psi":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                    case "kg/cm2":
                        res = formulas.FormulasPresion(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                }
                break;
        }
    }

    public void AsignacionFormulasCalorEnergia(EditText resultado, String tipo1, String tipo2, double cant)
    {
        double res = 0;
        switch (tipo1) {
            case "Joule":
                switch (tipo2) {
                    case "Joule":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "kg-m":
                        res = formulas.FormulasCalor(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "kcal":
                        res = formulas.FormulasCalor(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "kw-hr":
                        res = formulas.FormulasCalor(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "hp-hr":
                        res = formulas.FormulasCalor(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "cal":
                        res = formulas.FormulasCalor(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                }
                break;
            case "kcal":
                switch (tipo2) {
                    case "kcal":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "kg-m":
                        res = formulas.FormulasCalor(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "Joule":
                        res = formulas.FormulasCalor(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "kw-hr":
                        res = formulas.FormulasCalor(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "hp-hr":
                        res = formulas.FormulasCalor(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "cal":
                        res = formulas.FormulasCalor(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                }
                break;
            case "kg-m":
                switch (tipo2) {
                    case "kg-m":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "Joule":
                        res = formulas.FormulasCalor(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "kcal":
                        res = formulas.FormulasCalor(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "kw-hr":
                        res = formulas.FormulasCalor(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "hp-hr":
                        res = formulas.FormulasCalor(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "cal":
                        res = formulas.FormulasCalor(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                }
                break;
            case "kw-hr":
                switch (tipo2) {
                    case "kw-hr":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "kg-m":
                        res = formulas.FormulasCalor(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "kcal":
                        res = formulas.FormulasCalor(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "Joule":
                        res = formulas.FormulasCalor(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "hp-hr":
                        res = formulas.FormulasCalor(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "cal":
                        res = formulas.FormulasCalor(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                }
                break;
            case "hp-hr":
                switch (tipo2) {
                    case "hp-hr":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "kg-m":
                        res = formulas.FormulasCalor(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "kcal":
                        res = formulas.FormulasCalor(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "kw-hr":
                        res = formulas.FormulasCalor(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "Joule":
                        res = formulas.FormulasCalor(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "cal":
                        res = formulas.FormulasCalor(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                }
                break;
            case "cal":
                switch (tipo2) {
                    case "cal":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "kg-m":
                        res = formulas.FormulasCalor(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "kcal":
                        res = formulas.FormulasCalor(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "kw-hr":
                        res = formulas.FormulasCalor(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "hp-hr":
                        res = formulas.FormulasCalor(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "Joule":
                        res = formulas.FormulasCalor(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                }
                break;
        }
    }

    public void AsignacionFormulasVolumen(EditText resultado, String tipo1, String tipo2, double cant)
    {
        double res = 0;
        switch (tipo1) {
            case "m3":
                switch (tipo2) {
                    case "m3":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "lt":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "in3":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "ft3":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "yd3":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "gal":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "cm3":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                }
                break;
            case "lt":
                switch (tipo2) {
                    case "lt":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "m3":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "in3":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "ft3":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "yd3":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "gal":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "cm3":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                }
                break;
            case "in3":
                switch (tipo2) {
                    case "in3":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "lt":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "m3":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "ft3":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "yd3":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "gal":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "cm3":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                }
                break;
            case "ft3":
                switch (tipo2) {
                    case "ft3":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "lt":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "in3":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "m3":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "yd3":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "gal":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "cm3":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                }
                break;
            case "yd3":
                switch (tipo2) {
                    case "yd3":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "lt":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "in3":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "ft3":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "m3":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "gal":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "cm3":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                }
                break;
            case "gal":
                switch (tipo2) {
                    case "gal":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "lt":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "in3":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "ft3":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "yd3":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "m3":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "cm3":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                }
                break;
            case "cm3":
                switch (tipo2) {
                    case "cm3":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "lt":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "in3":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "ft3":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "yd3":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "gal":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "m3":
                        res = formulas.FormulasVolumen(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                }
                break;
        }
    }

    public void AsignacionFormulasPotencia(EditText resultado, String tipo1, String tipo2, double cant)
    {
        double res = 0;
        switch (tipo1) {
            case "kw":
                switch (tipo2) {
                    case "kw":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "hp":
                        res = formulas.FormulasPotencia(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "btu/hr":
                        res = formulas.FormulasPotencia(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "watts":
                        res = formulas.FormulasPotencia(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                }
                break;
            case "hp":
                switch (tipo2) {
                    case "hp":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "kw":
                        res = formulas.FormulasPotencia(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "btu/hr":
                        res = formulas.FormulasPotencia(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "watts":
                        res = formulas.FormulasPotencia(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                }
                break;
            case "btu/hr":
                switch (tipo2) {
                    case "btu/hr":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "hp":
                        res = formulas.FormulasPotencia(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "kw":
                        res = formulas.FormulasPotencia(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "watts":
                        res = formulas.FormulasPotencia(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                }
                break;
            case "watts":
                switch (tipo2) {
                    case "watts":
                        resultado.setText(String.valueOf(cant));
                        break;
                    case "hp":
                        res = formulas.FormulasPotencia(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "btu/hr":
                        res = formulas.FormulasPotencia(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                    case "kw":
                        res = formulas.FormulasPotencia(cant, tipo1, tipo2);
                        resultado.setText(formato.format(res));
                        break;
                }
                break;
        }
    }

}
