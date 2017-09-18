package app.roque.com.moneyhelper2.repository;

import java.util.ArrayList;
import java.util.List;

import app.roque.com.moneyhelper2.model.Operation;

/**
 * Created by keving on 18/09/2017.
 */


public class OperationRepository {

    public static OperationRepository _INSTANCE = null;

    private OperationRepository(){}

    public static OperationRepository getInstance(){
        if(_INSTANCE == null)
            _INSTANCE = new OperationRepository();
        return _INSTANCE;
    }

    private List<Operation> operations = new ArrayList<>();

    public List<Operation> getOperations() {return this.operations;}

    public void addOperation(Operation operation){
        this.operations.add(operation);
    }

}