package com.example.masterdetail;import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


public class ActivityListaArticulos extends AppCompatActivity
        implements FragmentListaArticulos.EscuchaFragmento {

    private boolean dosPaneles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_articulos);

        ((Toolbar) findViewById(R.id.toolbar)).setTitle(getTitle());

        if (findViewById(R.id.contenedor_detalle_articulo) != null) {
            dosPaneles = true;

            cargarFragmentoDetalle(ModeloArticulos.ITEMS.get(0).id);
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor_lista, FragmentListaArticulos.crear())
                .commit();

    }

    private void cargarFragmentoDetalle(String id) {
        Bundle arguments = new Bundle();
        arguments.putString(FragmentDetalleArticulo.ID_ARTICULO, id);
        FragmentDetalleArticulo fragment = new FragmentDetalleArticulo();
        fragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedor_detalle_articulo, fragment)
                .commit();
    }


    @Override
    public void alSeleccionarItem(String idArticulo) {
        if (dosPaneles) {
            cargarFragmentoDetalle(idArticulo);
        } else {
            Intent intent = new Intent(this, ActivityDetalleArticulo.class);
            intent.putExtra(FragmentDetalleArticulo.ID_ARTICULO, idArticulo);

            startActivity(intent);
        }
    }
}