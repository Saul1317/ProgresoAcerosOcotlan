package com.acerosocotlan.progresoacerosocotlan.Controlador.Otros.Convertidor;

/**
 * Created by rvazquez on 16/05/2017.
 */
public class Formulas
{
    /*
   *  FORMULAS PESO
   * */

    public double FormulasPeso(double cant, String tipo1, String tipo2)
    {
        double unidad = 0;
        if(tipo1.equals("lb/p")){
            switch (tipo2)
            {
                case "kg/m":
                    unidad = cant * 1.48816;//cant * 1.49;
                    return unidad;
                case "gr":
                    unidad = 0;
                    return unidad;
                case "tonelada":
                    unidad = 0;
                    return unidad;
                case "oz":
                    unidad = 0;
                    return unidad;
            }
        }
        if(tipo1.equals("kg/m")){
            switch (tipo2)
            {
                case "lb/p":
                    unidad = cant / 1.48816;///cant / 1.49;
                    return unidad;
                case "gr":
                    unidad = 0;
                    return unidad;
                case "tonelada":
                    unidad = 0;
                    return unidad;
                case "oz":
                    unidad = 0;
                    return unidad;
            }
        }
        if(tipo1.equals("lb")){
            switch (tipo2)
            {
                case "kg":
                    unidad = cant / 2.2046;
                    return unidad;
                case "gr":
                    unidad = cant / 0.0022046;
                    return unidad;
                case "tonelada":
                    unidad = cant / 2204.6;
                    return unidad;
                case "oz":
                    unidad = cant * 16.000;
                    return unidad;
            }
        }
        else if(tipo1.equals("kg"))
        {
            switch (tipo2)
            {
                case "lb":
                    unidad = cant * 2.2046;
                    return unidad;
                case "gr":
                    unidad = cant / 0.0010000;
                    return unidad;
                case "tonelada":
                    unidad = cant / 1000.0;
                    return unidad;
                case "oz":
                    unidad = cant * 35.274;
                    return unidad;
            }
        }
        else if(tipo1.equals("gr"))
        {
            switch (tipo2)
            {
                case "lb":
                    unidad = cant * 0.0022046;
                    return unidad;
                case "kg":
                    unidad = cant / 1000.0;
                    return unidad;
                case "tonelada":
                    unidad = cant / 1000000;
                    return unidad;
                case "oz":
                    unidad = cant * 0.035274;
                    return unidad;
            }
        }
        else if(tipo1.equals("oz"))
        {
            switch (tipo2)
            {
                case "lb":
                    unidad = cant * 0.062500;
                    return unidad;
                case "gr":
                    unidad = cant / 0.035274;
                    return unidad;
                case "tonelada":
                    unidad = cant / 35274;
                    return unidad;
                case "kg":
                    unidad = cant / 35.274;
                    return unidad;
            }
        }
        else if(tipo1.equals("tonelada"))
        {
            switch (tipo2)
            {
                case "lb":
                    unidad = cant * 2204.62;
                    return unidad;
                case "gr":
                    unidad = cant / 0.0000010000;
                    return unidad;
                case "kg":
                    unidad = cant / 0.0010000;
                    return unidad;
                case "oz":
                    unidad = cant * 35274;
                    return unidad;
            }
        }
        return 0;
    }
    /*
    * INICIA FORMULAS LONGITUD
    * */


    public double FormulasLongitud(double cant, String tipo1, String tipo2)
    {
        double unidad = 0;
        if(tipo1.equals("in")){
            switch (tipo2)
            {
                case "cm":
                    unidad = cant / 0.39370;
                    return unidad;
                case "mm":
                    unidad = cant / 0.039370;
                    return unidad;
                case "m":
                    unidad = cant / 39.370;
                    return unidad;
                case "km":
                    unidad = cant / 39370;
                    return unidad;
                case "ft":
                    unidad = cant * 0.083333;
                    return unidad;
                case "milla":
                    unidad = cant * 0.000015783;
                    return unidad;
                case "yd":
                    unidad = cant * 0.027778;
                    return unidad;
            }
        }
        else if(tipo1.equals("m"))
        {
            switch (tipo2)
            {
                case "cm":
                    unidad = cant / 0.010000;
                    return unidad;
                case "in":
                    unidad = cant * 39.370;
                    return unidad;
                case "mm":
                    unidad = cant / 0.0010000;
                    return unidad;
                case "km":
                    unidad = cant / 1000;
                    return unidad;
                case "ft":
                    unidad = cant * 3.2808;
                    return unidad;
                case "milla":
                    unidad = cant * 0.00062137;
                    return unidad;
                case "yd":
                    unidad = cant * 1.0936;
                    return unidad;
            }
        }
        else if(tipo1.equals("cm"))
        {
            switch (tipo2)
            {
                case "mm":
                    unidad = cant / 0.10000;
                    return unidad;
                case "in":
                    unidad = cant * 0.39370;
                    return unidad;
                case "m":
                    unidad = cant / 100;
                    return unidad;
                case "km":
                    unidad = cant / 100000;
                    return unidad;
                case "ft":
                    unidad = cant * 0.0032808;
                    return unidad;
                case "milla":
                    unidad = cant * 0.0000062137;
                    return unidad;
                case "yd":
                    unidad = cant * 0.010936;
                    return unidad;
            }
        }
        else if(tipo1.equals("mm"))
        {
            switch (tipo2)
            {
                case "cm":
                    unidad = cant / 10;
                    return unidad;
                case "in":
                    unidad = cant * 0.039370;
                    return unidad;
                case "m":
                    unidad = cant / 1000;
                    return unidad;
                case "km":
                    unidad = cant / 1000000;
                    return unidad;
                case "ft":
                    unidad = cant * 0.0032808;
                    return unidad;
                case "milla":
                    unidad = cant * 0.00000062137;
                    return unidad;
                case "yd":
                    unidad = cant * 0.0010936;
                    return unidad;

            }
        }
        else if(tipo1.equals("km"))
        {
            switch (tipo2)
            {
                case "cm":
                    unidad = cant / 0.000010000;
                    return unidad;
                case "in":
                    unidad = cant * 39370.08;
                    return unidad;
                case "m":
                    unidad = cant / 0.0010000;
                    return unidad;
                case "mm":
                    unidad = cant / 0.0000010000;
                    return unidad;
                case "ft":
                    unidad = cant * 3280.8;
                    return unidad;
                case "milla":
                    unidad = cant * 0.62137;
                    return unidad;
                case "yd":
                    unidad = cant * 1093.6;
                    return unidad;
            }
        }
        else if(tipo1.equals("ft"))
        {
            switch (tipo2)
            {
                case "cm":
                    unidad = cant / 0.032808;
                    return unidad;
                case "in":
                    unidad = cant * 12.000;
                    return unidad;
                case "m":
                    unidad = cant / 3.2808;
                    return unidad;
                case "km":
                    unidad = cant / 3280.8;
                    return unidad;
                case "mm":
                    unidad = cant / 0.0032808;
                    return unidad;
                case "milla":
                    unidad = cant * 0.00018939;
                    return unidad;
                case "yd":
                    unidad = cant * 0.33333;
                    return unidad;
            }
        }
        else if(tipo1.equals("yd"))
        {
            switch (tipo2)
            {
                case "cm":
                    unidad = cant / 0.010936;
                    return unidad;
                case "in":
                    unidad = cant * 36.000;
                    return unidad;
                case "m":
                    unidad = cant / 1.0936;
                    return unidad;
                case "km":
                    unidad = cant / 1093.6;
                    return unidad;
                case "ft":
                    unidad = cant * 3.0000;
                    return unidad;
                case "milla":
                    unidad = cant * 0.00056818;
                    return unidad;
                case "mm":
                    unidad = cant / 0.0010936;
                    return unidad;
            }
        }
        else if(tipo1.equals("milla"))
        {
            switch (tipo2)
            {
                case "cm":
                    unidad = cant / 0.0000062137;
                    return unidad;
                case "in":
                    unidad = cant * 63360;
                    return unidad;
                case "m":
                    unidad = cant / 0.00062137;
                    return unidad;
                case "km":
                    unidad = cant / 0.62137;
                    return unidad;
                case "ft":
                    unidad = cant * 5280.0;
                    return unidad;
                case "mm":
                    unidad = cant / 0.00000062137;
                    return unidad;
                case "yd":
                    unidad = cant * 1760.0;
                    return unidad;
            }
        }
        return 0;
    }

    /*
    * FORMULAS TEMPERATURA
    * */

    public double FormulasTemperatura(double cant, String tipo1, String tipo2)
    {
        double unidad = 0;
        if(tipo1.equals("°F"))
        {
            switch (tipo2)
            {
                case "°C":
                    unidad = (cant - 32) / 1.8000;
                    return unidad;
            }
        }
        else if(tipo1.equals("°C"))
        {
            switch (tipo2)
            {
                case "°F":
                    unidad = (cant * 1.8000) + 32;
                    return unidad;
            }
        }
        return 0;
    }

    /*
    * FORMULAS AREAS
    * */

    public double FormulasAreas(double cant, String tipo1, String tipo2)
    {
        double unidad = 0;
        if(tipo1.equals("cm2"))
        {
            switch (tipo2)
            {
                case "m2":
                    unidad = cant / 10000;
                    return unidad;
                case "km2":
                    unidad = cant / (10000 * 1000000);
                    return unidad;
                case "ha":
                    unidad = cant / 100000000;
                    return unidad;
                case "in2":
                    unidad = cant * 0.15500;
                    return unidad;
                case "ft2":
                    unidad = cant * 0.0010764;
                    return unidad;
                case "yd2":
                    unidad = cant * 0.00011960;
                    return unidad;
                case "milla2":
                    unidad = cant * 0.000000000038610;
                    return unidad;
                case "acre2":
                    unidad = cant * 1760.0;
                    return unidad;
                case "mm2":
                    unidad = cant * 1760.0;
                    return unidad;
            }
        }
        else   if(tipo1.equals("m2"))
        {
            switch (tipo2)
            {
                case "cm2":
                    unidad = cant / 0.00010000;
                    return unidad;
                case "km2":
                    unidad = cant / 1000000;
                    return unidad;
                case "ha":
                    unidad = cant / 10000;
                    return unidad;
                case "in2":
                    unidad = cant * 1550.0;
                    return unidad;
                case "ft2":
                    unidad = cant * 10.764;
                    return unidad;
                case "yd2":
                    unidad = cant * 1.1960;
                    return unidad;
                case "milla2":
                    unidad = cant * 0.00000038610;
                    return unidad;
                case "acre2":
                    unidad = cant * 0.00024711;
                    return unidad;
                case "mm2":
                    unidad = cant / 0.0000010000;
                    return unidad;
            }
        }
        if(tipo1.equals("km2"))
        {
            switch (tipo2)
            {
                case "cm2":
                    unidad = cant / 0.00000000010000;
                    return unidad;
                case "m2":
                    unidad = cant / 0.0000010000;
                    return unidad;
                case "ha":
                    unidad = cant / 0.010000;
                    return unidad;
                case "in2":
                    unidad = cant * 1550000000;
                    return unidad;
                case "ft2":
                    unidad = cant * 10764000;
                    return unidad;
                case "yd2":
                    unidad = cant * 1196000;
                    return unidad;
                case "milla2":
                    unidad = cant * 0.38610;
                    return unidad;
                case "acre2":
                    unidad = cant * 247.11;
                    return unidad;
                case "mm2":
                    unidad = cant / 0.0000000000010000;
                    return unidad;
            }
        }
        if(tipo1.equals("ha"))
        {
            switch (tipo2)
            {
                case "cm2":
                    unidad = cant / 0.000000010000;
                    return unidad;
                case "km2":
                    unidad = cant / 100.00;
                    return unidad;
                case "m2":
                    unidad = cant / 0.00010000;
                    return unidad;
                case "in2":
                    unidad = cant * 15500000;
                    return unidad;
                case "ft2":
                    unidad = cant * 107640;
                    return unidad;
                case "yd2":
                    unidad = cant * 11960;
                    return unidad;
                case "milla2":
                    unidad = cant * 0.0038610;
                    return unidad;
                case "acre2":
                    unidad = cant * 2.4711;
                    return unidad;
                case "mm2":
                    unidad = cant / 0.00000000010000;
                    return unidad;
            }
        }
        if(tipo1.equals("in2"))
        {
            switch (tipo2)
            {
                case "cm2":
                    unidad = cant / 0.15500;
                    return unidad;
                case "km2":
                    unidad = cant / 1550000000;
                    return unidad;
                case "ha":
                    unidad = cant / 15500000;
                    return unidad;
                case "m2":
                    unidad = cant / 1550.0;
                    return unidad;
                case "ft2":
                    unidad = cant * 0.0069444;
                    return unidad;
                case "yd2":
                    unidad = cant * 0.00077160;
                    return unidad;
                case "milla2":
                    unidad = cant  * 0.00000000024910;
                    return unidad;
                case "acre2":
                    unidad = cant * 0.00000015942;
                    return unidad;
                case "mm2":
                    unidad = cant / 0.0015500;
                    return unidad;
            }
        }
        if(tipo1.equals("ft2"))
        {
            switch (tipo2)
            {
                case "cm2":
                    unidad = cant / 0.0010764;
                    return unidad;
                case "km2":
                    unidad = cant / 10764000;
                    return unidad;
                case "ha":
                    unidad = cant / 107640;
                    return unidad;
                case "in2":
                    unidad = cant * 144.00;
                    return unidad;
                case "m2":
                    unidad = cant / 10.764;
                    return unidad;
                case "yd2":
                    unidad = cant * 0.11111;
                    return unidad;
                case "milla2":
                    unidad = cant * 0.000000035870;
                    return unidad;
                case "acre2":
                    unidad = cant * 0.000022957;
                    return unidad;
                case "mm2":
                    unidad = cant / 0.000010764;
                    return unidad;
            }
        }
        if(tipo1.equals("yd2"))
        {
            switch (tipo2)
            {
                case "cm2":
                    unidad = cant / 0.00011960;
                    return unidad;
                case "km2":
                    unidad = cant / 1196000;
                    return unidad;
                case "ha":
                    unidad = cant / 11960;
                    return unidad;
                case "in2":
                    unidad = cant * 1296.0;
                    return unidad;
                case "ft2":
                    unidad = cant * 1760.0;
                    return unidad;
                case "m2":
                    unidad = cant / 1.1960;
                    return unidad;
                case "milla2":
                    unidad = cant * 0.00000032283;
                    return unidad;
                case "acre2":
                    unidad = cant * 0.00020661;
                    return unidad;
                case "mm2":
                    unidad = cant / 0.0000011960;
                    return unidad;
            }
        }
        if(tipo1.equals("milla2"))
        {
            switch (tipo2)
            {
                case "cm2":
                    unidad = cant / 0.000000000038610;
                    return unidad;
                case "km2":
                    unidad = cant / 0.38610;
                    return unidad;
                case "ha":
                    unidad = cant / 0.0038610;
                    return unidad;
                case "in2":
                    unidad = 0;
                    return unidad;
                case "ft2":
                    unidad = cant * 27878000;
                    return unidad;
                case "yd2":
                    unidad = cant * 3097600;
                    return unidad;
                case "m2":
                    unidad = cant / 0.00000038610;
                    return unidad;
                case "acre2":
                    unidad = cant * 640.00;
                    return unidad;
                case "mm2":
                    unidad = cant / 0.00000000000038610;
                    return unidad;
            }
        }
        if(tipo1.equals("acre2"))
        {
            switch (tipo2)
            {
                case "cm2":
                    unidad = cant / 0.00010000;
                    return unidad;
                case "km2":
                    unidad = cant / 247.11;
                    return unidad;
                case "ha":
                    unidad = cant / 2.4711;
                    return unidad;
                case "in2":
                    unidad = cant * 0.00000015942;
                    return unidad;
                case "ft2":
                    unidad = cant * 43560;
                    return unidad;
                case "yd2":
                    unidad = cant * 4840.0;
                    return unidad;
                case "milla2":
                    unidad = cant * 0.0015625;
                    return unidad;
                case "m2":
                    unidad = cant / 0.00024711;
                    return unidad;
                case "mm2":
                    unidad = cant / 0.00000000024711;
                    return unidad;
            }
        }
        if(tipo1.equals("mm2"))
        {
            switch (tipo2)
            {
                case "cm2":
                    unidad = cant / 100.00;
                    return unidad;
                case "km2":
                    unidad = cant / (1000000 * 1000000);
                    return unidad;
                case "ha":
                    unidad = cant / (100000 * 100000);
                    return unidad;
                case "in2":
                    unidad = cant / 0.0015500;
                    return unidad;
                case "ft2":
                    unidad = cant * 0.000010764;
                    return unidad;
                case "yd2":
                    unidad = cant * 0.0000011960;
                    return unidad;
                case "milla2":
                    unidad = cant * 0.00000000000038610;
                    return unidad;
                case "acre2":
                    unidad = cant * 0.00000000024711;
                    return unidad;
                case "m2":
                    unidad = cant / 1000000;
                    return unidad;
            }
        }
        return 0;
    }

    /*
    * FORMULAS PRESIÓN
    * */

    public double FormulasPresion(double cant, String tipo1, String tipo2)
    {
        double unidad = 0;
        if(tipo1.equals("kg/cm2"))
        {
            switch (tipo2)
            {
                case "kgf/mm2":
                    unidad = cant * 0.01;
                    return unidad;
                case "mm HG":
                    unidad = cant * 735.6;
                    return unidad;
                case "Mpa":
                    unidad = cant * 0.0980665;
                    return unidad;
                case "N/mm2":
                    unidad = cant * 0.0980665;
                    return unidad;
                case "psi":
                    unidad = cant * 14.22334;
                    return unidad;
                case "Atm":
                    unidad = cant * 0.9678;
                    return unidad;
            }
        }
        if(tipo1.equals("kgf/mm2"))
        {
            switch (tipo2)
            {
                case "kg/cm2":
                    unidad = cant * 100;
                    return unidad;
                case "mm HG":
                    unidad = cant * 73535.460784;
                    return unidad;
                case "Mpa":
                    unidad = cant * 9.80665;
                    return unidad;
                case "N/mm2":
                    unidad = cant * 9.80665;
                    return unidad;
                case "psi":
                    unidad = cant * 1421.941176;
                    return unidad;
                case "Atm":
                    unidad = cant * 96.757185 ;
                    return unidad;
            }
        }
        if(tipo1.equals("mm HG"))
        {
            switch (tipo2)
            {
                case "kgf/mm2":
                    unidad = cant * 0.01934;
                    return unidad;
                case "kg/cm2":
                    unidad = cant * 0.00136;
                    return unidad;
                case "Mpa":
                    unidad = cant * 0.0001333224;
                    return unidad;
                case "N/mm2":
                    unidad = cant * 0.0001333224;
                    return unidad;
                case "psi":
                    unidad = cant * 0.01934;
                    return unidad;
                case "Atm":
                    unidad = cant * 0.001316;
                    return unidad;
            }
        }
        if(tipo1.equals("Mpa"))
        {
            switch (tipo2)
            {
                case "kgf/mm2":
                    unidad = cant * 0.1019716213;
                    return unidad;
                case "mm HG":
                    unidad = cant * 7500.6150504341;
                    return unidad;
                case "kg/cm2":
                    unidad = cant * 10.1972;
                    return unidad;
                case "N/mm2":
                    unidad = cant * 1;
                    return unidad;
                case "psi":
                    unidad = cant * 145.0377438973;
                    return unidad;
                case "Atm":
                    unidad = cant * 9.8692326672;
                    return unidad;
            }
        }
        if(tipo1.equals("N/mm2"))
        {
            switch (tipo2)
            {
                case "kgf/mm2":
                    unidad = cant * 0.1019716213;
                    return unidad;
                case "mm HG":
                    unidad = cant * 7500.6150504341;
                    return unidad;
                case "Mpa":
                    unidad = cant * 1;
                    return unidad;
                case "kg/cm2":
                    unidad = cant * 10.1972;
                    return unidad;
                case "psi":
                    unidad = cant * 145.0377438973;
                    return unidad;
                case "Atm":
                    unidad = cant * 9.8692326672;
                    return unidad;
            }
        }
        if(tipo1.equals("psi"))
        {
            switch (tipo2)
            {
                case "kgf/mm2":
                    unidad  = cant * 0.000703264;
                    return unidad;
                case "mm HG":
                    unidad  = cant * 51.715;
                    return unidad;
                case "Mpa":
                    unidad  = cant * 0.00689476;
                    return unidad;
                case "N/mm2":
                    unidad  = cant * 0.00689476;
                    return unidad;
                case "kg/cm2":
                    unidad  = cant * 0.07031;
                    return unidad;
                case "Atm":
                    unidad  = cant * 0.068046;
                    return unidad;
            }
        }
        if(tipo1.equals("Atm"))
        {
            switch (tipo2)
            {
                case "kgf/mm2":
                    unidad = cant * 0.01033515;
                    return unidad;
                case "mm HG":
                    unidad = cant * 760;
                    return unidad;
                case "Mpa":
                    unidad = cant * 0.101325;
                    return unidad;
                case "N/mm2":
                    unidad = cant * 0.101325;
                    return unidad;
                case "psi":
                    unidad = cant * 14.6959;
                    return unidad;
                case "kg/cm2":
                    unidad = cant * 1033;
                    return unidad;
            }
        }
        return 0;
    }

    /*
    * FORMULAS CALOR
    * */

    public double FormulasCalor(double cant, String tipo1, String tipo2)
    {
        double unidad = 0;
        if(tipo1.equals("Joule"))
        {
            switch (tipo2)
            {
                case "kcal":
                    unidad = cant * 0.000239006;
                    return unidad;
                case "kg-m":
                    unidad = cant * 0.1019716213009;
                    return unidad;
                case "kw-hr":
                    unidad = cant * (2.777777777778*10-07);
                    return unidad;
                case "cal":
                    unidad = cant * 0.23900573614;
                    return unidad;
                case "hp-hr":
                    unidad = cant * 3.725061361111E-07;
                    return unidad;
            }
        }
        if(tipo1.equals("kcal"))
        {
            switch (tipo2)
            {
                case "Joule":
                    unidad = cant * 4186.8;
                    return unidad;
                case "kg-m":
                    unidad = cant * 426.9347840628;
                    return unidad;
                case "kw-hr":
                    unidad = cant * 0.001163;
                    return unidad;
                case "cal":
                    unidad = cant * 1000;
                    return unidad;
                case "hp-hr":
                    unidad = cant * 0.00155960869067;
                    return unidad;
            }
        }
        if(tipo1.equals("kg-m"))
        {
            switch (tipo2)
            {
                case "Joule":
                    unidad = cant * 9.806649999698;
                    return unidad;
                case "kcal":
                    unidad = cant * 0.00234227811209;
                    return unidad;
                case "kw-hr":
                    unidad = cant * 0.00000272406944436;
                    return unidad;
                case "cal":
                    unidad = cant * 2.34227811209;
                    return unidad;
                case "hp-hr":
                    unidad = cant * 0.000003653037299581;
                    return unidad;
            }
        }
        if(tipo1.equals("kw-hr"))
        {
            switch (tipo2)
            {
                case "Joule":
                    unidad = cant * 9.806649999698;
                    return unidad;
                case "kcal":
                    unidad = cant * 859.845227859;
                    return unidad;
                case "kg-m":
                    unidad = cant * 367097.8366834;
                    return unidad;
                case "cal":
                    unidad = cant * 859845.227859;
                    return unidad;
                case "hp-hr":
                    unidad = cant * 1.34102209;
                    return unidad;
            }
        }
        if(tipo1.equals("cal"))
        {
            switch (tipo2)
            {
                case "Joule":
                    unidad = cant * 4.1868;
                    return unidad;
                case "kcal":
                    unidad = cant * 0.001;
                    return unidad;
                case "kg-m":
                    unidad = cant * 0.4269347840628;
                    return unidad;
                case "kw-hr":
                    unidad = cant * 0.000001163;
                    return unidad;
                case "hp-hr":
                    unidad = cant * 0.00000155960869067;
                    return unidad;
            }
        }
        if(tipo1.equals("hp-hr"))
        {
            switch (tipo2)
            {
                case "Joule":
                    unidad = cant * 2684519.536886;
                    return unidad;
                case "kcal":
                    unidad = cant * 641.1864758015;
                    return unidad;
                case "kg-m":
                    unidad = cant * 273744.8095903;
                    return unidad;
                case "kw-hr":
                    unidad = cant * 0.7456998713571;
                    return unidad;
                case "cal":
                    unidad = cant * 641186.4758015;
                    return unidad;
            }
        }
        return 0;
    }

    /*
    * FORMULAS POTENCIA
    * */

    public double FormulasPotencia(double cant, String tipo1, String tipo2)
    {
        double unidad = 0;
        if(tipo1.equals("kw"))
        {
            switch (tipo2)
            {
                case "hp":
                    unidad = cant * 1.341022089595;
                    return unidad;
                case "btu/hr":
                    unidad = cant * 3412.141633128;
                    return unidad;
                case "watts":
                    unidad = cant * 1000;
                    return unidad;
            }
        }
        if(tipo1.equals("hp"))
        {
            switch (tipo2)
            {
                case "kw":
                    unidad = cant * 0.7456998715823;
                    return unidad;
                case "btu/hr":
                    unidad = cant * 2546.136992233;
                    return unidad;
                case "watts":
                    unidad = cant * 745.6998715823;
                    return unidad;
            }
        }
        if(tipo1.equals("btu/hr"))
        {
            switch (tipo2)
            {
                case "hp":
                    unidad = cant * 0.0003927518444806;
                    return unidad;
                case "kw":
                    unidad = cant * 0.0002928749999929;
                    return unidad;
                case "watts":
                    unidad = cant * 0.2928749999929;
                    return unidad;
            }
        }
        if(tipo1.equals("watts"))
        {
            switch (tipo2)
            {
                case "hp":
                    unidad = cant * 0.001341022089595;
                    return unidad;
                case "btu/hr":
                    unidad = cant * 3.41442594972;
                    return unidad;
                case "kw":
                    unidad = cant * 0.01;
                    return unidad;
            }
        }
        return 0;
    }

    public double FormulasVolumen(double cant, String tipo1, String tipo2)
    {
        double unidad = 0;
        if(tipo1.equals("cm3"))
        {
            switch (tipo2)
            {
                case "m3":
                    unidad = cant * 0.000001;
                    return unidad;
                case "lt":
                    unidad = cant *  0.001;
                    return unidad;
                case "in3":
                    unidad = cant * 0.06102374409473;
                    return unidad;
                case "ft3":
                    unidad = cant * 0.00003531466672149;
                    return unidad;
                case "yd3":
                    unidad = cant * 0.000001307950619314;
                    return unidad;
                case "gal":
                    unidad = cant * 0.0002641720523581;
                    return unidad;
            }
        }
        if(tipo1.equals("m3"))
        {
            switch (tipo2)
            {
                case "cm3":
                    unidad = cant * 1000000;
                    return unidad;
                case "lt":
                    unidad = cant * 1000;
                    return unidad;
                case "in3":
                    unidad = cant * 61023.74409473;
                    return unidad;
                case "ft3":
                    unidad = cant * 35.31466672149;
                    return unidad;
                case "yd3":
                    unidad = cant * 1.307950619314;
                    return unidad;
                case "gal":
                    unidad = cant * 264.1720523581;
                    return unidad;
            }
        }
        if(tipo1.equals("lt"))
        {
            switch (tipo2)
            {
                case "m3":
                    unidad = cant * 0.001;
                    return unidad;
                case "cm3":
                    unidad = cant * 1000;
                    return unidad;
                case "in3":
                    unidad = cant * 61.02374409473;
                    return unidad;
                case "ft3":
                    unidad = cant * 0.03531466672149;
                    return unidad;
                case "yd3":
                    unidad = cant * 0.001307950619314;
                    return unidad;
                case "gal":
                    unidad = cant * 0.2641720523581;
                    return unidad;
            }
        }
        if(tipo1.equals("in3"))
        {
            switch (tipo2)
            {
                case "m3":
                    unidad = cant * 0.000016387064;
                    return unidad;
                case "lt":
                    unidad = cant * 0.016387064;
                    return unidad;
                case "cm3":
                    unidad = cant * 16.387064;
                    return unidad;
                case "ft3":
                    unidad = cant * 0.0005787037037037;
                    return unidad;
                case "yd3":
                    unidad = cant * 0.00002143347050754;
                    return unidad;
                case "gal":
                    unidad = cant * 0.004329004329004;
                    return unidad;
            }
        }
        if(tipo1.equals("ft3"))
        {
            switch (tipo2)
            {
                case "m3":
                    unidad = cant * 0.028316846592;
                    return unidad;
                case "lt":
                    unidad = cant * 28.316846592;
                    return unidad;
                case "in3":
                    unidad = cant * 1728;
                    return unidad;
                case "cm3":
                    unidad = cant * 28316.846592;
                    return unidad;
                case "yd3":
                    unidad = cant * 0.03703703703703;
                    return unidad;
                case "gal":
                    unidad = cant * 7.480519480519;
                    return unidad;
            }
        }
        if(tipo1.equals("yd3"))
        {
            switch (tipo2)
            {
                case "m3":
                    unidad = cant * 0.7645548579839;
                    return unidad;
                case "lt":
                    unidad = cant * 764.5548579839;
                    return unidad;
                case "in3":
                    unidad = cant * 46656;
                    return unidad;
                case "ft3":
                    unidad = cant * 27;
                    return unidad;
                case "cm3":
                    unidad = cant * 764554.8579839;
                    return unidad;
                case "gal":
                    unidad = cant * 201.974025974;
                    return unidad;
            }
        }
        if(tipo1.equals("gal"))
        {
            switch (tipo2)
            {
                case "m3":
                    unidad = cant * 0.003785411784;
                    return unidad;
                case "lt":
                    unidad = cant * 3.785411784;
                    return unidad;
                case "in3":
                    unidad = cant * 231;
                    return unidad;
                case "ft3":
                    unidad = cant * 0.1336805555556;
                    return unidad;
                case "yd3":
                    unidad = cant * 0.004951131687243;
                    return unidad;
                case "cm3":
                    unidad = cant * 3785.411784;
                    return unidad;
            }
        }
        return 0;
    }

}
