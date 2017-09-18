package app.roque.com.moneyhelper2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import app.roque.com.moneyhelper2.model.Operation;
import app.roque.com.moneyhelper2.repository.OperationRepository;

public class MainActivity extends AppCompatActivity {

    private static final int REGISTER_FORM_REQUEST = 100;
    private TextView ahorro,credito,efectivo;
    private String tipoEn;
    private String tiposSpi;
    private double montoTarjeta, montoEfectivo, montoAhorro;
    private double montop;
    private ProgressBar progressBar;
    private double totalProgressI;
    private double totalProgressE;
    private int proI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ahorro = (TextView) findViewById(R.id.montoAhorro);
        credito = (TextView) findViewById(R.id.montoCredito);
        efectivo = (TextView) findViewById(R.id.montoEfectivo);
        progressBar = (ProgressBar) findViewById(R.id.progressbar1);
    }
    public void addItem(View view){
        startActivityForResult(new Intent(this, NewOperationActivity.class), 100);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        OperationRepository operationRepository = OperationRepository.getInstance();

        List<Operation> operations = operationRepository.getOperations();
        for (Operation operation : operations) {
            //comentario
            tipoEn = operation.getTipoE();
            tiposSpi = operation.getTarjett();
            montop = operation.getMonto();


        }

        switch (tipoEn){
            case "Ingreso":
                totalProgressI = totalProgressI + montop;
                switch (tiposSpi){
                    case "Tarjeta de Crédito":
                        montoTarjeta=montoTarjeta+montop;
                        break;
                    case "Ahorro":
                        montoAhorro=montoAhorro+montop;
                        break;
                    case "Efectivo":
                        montoEfectivo=montoEfectivo+montop;
                        break;
                }
                break;
            case "Egreso":
                totalProgressE = totalProgressE + montop;
                switch (tiposSpi){
                    case "Tarjeta de Crédito":
                        montoTarjeta=montoTarjeta-montop;
                        break;
                    case "Ahorro":
                        montoAhorro=montoAhorro-montop;
                        break;
                    case "Efectivo":
                        montoEfectivo=montoEfectivo-montop;
                        break;
                }
                break;
        }

        credito.setText(String.valueOf(montoTarjeta));
        ahorro.setText(String.valueOf(montoAhorro));
        efectivo.setText(String.valueOf(montoEfectivo));
        double totalPro = totalProgressE + totalProgressI;
        totalProgressI = Math.round(totalProgressI*100/totalPro);
        proI = (int) totalProgressI;
        progressBar.setProgress(proI);




    }

}