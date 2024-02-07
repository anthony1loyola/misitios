package com.example.lugaresfavoritos;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.lugaresfavoritos.database.Persona;
import com.example.lugaresfavoritos.database.PersonaLab;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class contacto extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextNombre;
    private EditText editTextDireccion;
    private EditText editTextTelefono;
    private PersonaLab personaLab;
    private ImageButton mmageView;
    Button btnguardar;

    private final int REQUET_CODE_CAMERA = 1;
    private boolean control = false;
    private final String ruta_fotos = Environment.
            getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES)
            .getAbsolutePath() +
            "/ImagenApp";
    private File file = new File(ruta_fotos.toString());
    String imageFileName = "imagenApp";
    private final String TAC = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        btnguardar = (Button) findViewById(R.id.agregar);
        editTextNombre = findViewById(R.id.txt_nombre);

        editTextDireccion = findViewById(R.id.txt_direccion);
        editTextTelefono = findViewById(R.id.txt_numero);
        personaLab = new PersonaLab(this);
        mmageView = findViewById(R.id.imageButton2);
        mmageView.setOnClickListener(this);

        btnguardar.setOnClickListener(this);

    }

    public void onClick(View view) {
        if (view == btnguardar) {
            insertPersonas();
            getAllPersonas();

        }
        if (view == mmageView) {
            camaraLauncher.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
        }
    }


    public void insertPersonas() {

        // Obtener datos del formulario
        String nombre = editTextNombre.getText().toString();

        String direccion = editTextDireccion.getText().toString();
        String telefono = editTextTelefono.getText().toString();

        // Crear una instancia de Persona con los datos del formulario

        if (nombre.equals("")) {
            editTextNombre.setError("Ingrese el nombre");
        } else {
            Persona persona = new Persona();
            persona.setNombre(nombre);

            persona.setDireccion(direccion);
            persona.setTelefono(telefono);

            // Agregar la nueva persona a la base de datos utilizando PersonaLab
            personaLab.addPersona(persona);

            editTextNombre.setText("");

            editTextDireccion.setText("");
            editTextTelefono.setText("");

        }
    }


    public void getAllPersonas() {
        PersonaLab personaLab = PersonaLab.get(getApplicationContext());
        List<Persona> personas = personaLab.getPersonas();
        for (Persona p : personas) {
            Log.d("InserData", "Id:" + p.getId() + "Nombre: " + p.getNombre() +
                    " Direccion: " + p.getDireccion() +
                    " Telefono: " + p.getTelefono());
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.action_delete) {
            eliminarFoto();
        }
        if (item.getItemId() == R.id.action_save) {
            guardarImganen();
        }
        return true;
    }

    ActivityResultLauncher<Intent> camaraLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == RESULT_OK) {
                        Bundle extras = result.getData().getExtras();
                        mmageView.setImageBitmap((Bitmap) extras.get("data"));
                        mmageView.setImageBitmap((Bitmap) extras.get("data"));
                        mmageView.setVisibility(View.VISIBLE);
                        // mTextView.setVisibility(View.GONE);
                        control = true;
                    }
                }
            });

    public void guardarImganen() {
        FileOutputStream fos = null;

        try {
            if (control == true) {
                Bitmap imagen = ((BitmapDrawable) mmageView.getDrawable()).getBitmap();
                File image = new File(file, imageFileName + " .jpg");
                fos = new FileOutputStream(image);
                imagen.compress(Bitmap.CompressFormat.PNG, 100, fos);

                fos.close();
                Toast.makeText(getApplicationContext(), "LA IMAGEN SE GUARDO CORRECTAMENTE", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getApplicationContext(), "NO HAY IMAGEN PARA GURDAR", Toast.LENGTH_SHORT).show();
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void eliminarFoto() {
        if (control == true) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Esta seguro que deseas borrar la imagen");
            builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    File f = new File(file, imageFileName + ".jpg");
                    f.delete();
                    mmageView.setImageBitmap(null);
                    mmageView.setVisibility(View.VISIBLE);
                    control = false;
                }
            });
            builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        } else {
            Toast.makeText(this, "No hay foto", Toast.LENGTH_SHORT).show();
        }

    }

    public void iraalmapa(View vista) {
        Intent intent = new Intent(this, mimap.class);
        startActivity(intent);

    }
}