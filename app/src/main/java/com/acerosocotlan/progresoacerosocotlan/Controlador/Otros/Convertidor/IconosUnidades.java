package com.acerosocotlan.progresoacerosocotlan.Controlador.Otros.Convertidor;

/**
 * Created by rvazquez on 16/05/2017.
 */
public class IconosUnidades
{
    private String name;

    private int icon;

    public IconosUnidades(String nombre, int icono)
    {
        super();
        this.name = nombre;
        this.icon = icono;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getIcon()
    {
        return icon;
    }

    public void setIcon(int icon)
    {
        this.icon = icon;
    }
}
