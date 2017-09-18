package app.roque.com.moneyhelper2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import app.roque.com.moneyhelper2.model.Operation;
import app.roque.com.moneyhelper2.repository.OperationRepository;

public class NewOperationActivity extends AppCompatActivity {
    private Spinner tipo;
    private EditText newMonto;
    private RadioGroup tipoE;
    private String cuentaTip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_operation);

        newMonto = (EditText) findViewById(R.id.newMonto);
        tipoE = (RadioGroup) findViewById(R.id.radioGroup);

        tipo = (Spinner) findViewById(R.id.tipo_cuenta);

    }

    public void newRegister(View view) {

        String montos = newMonto.getText().toString();
        int monimput = Integer.parseInt(montos);
        String tipes = ((RadioButton) findViewById(tipoE.getCheckedRadioButtonId())).getText().toString();

        String tipos = tipo.getSelectedItem().toString();

        if (montos.isEmpty() || tipes.isEmpty() || tipos.isEmpty()) {
            Toast.makeText(this, "Rellene los datos", Toast.LENGTH_SHORT).show();
            return;
        }

        Operation operation = new Operation(monimput, tipes, tipos);
        OperationRepository operationRepository = OperationRepository.getInstance();
        operationRepository.addOperation(operation);
        finish();


    }
}
