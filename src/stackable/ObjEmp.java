package stackable;

import stackable.exceptions.IncorrectOperation;

public interface ObjEmp {
    ObjEmp add(ObjEmp o) throws IncorrectOperation;
    ObjEmp sub(ObjEmp o) throws IncorrectOperation;
    ObjEmp mul(ObjEmp o) throws IncorrectOperation;
    ObjEmp div(ObjEmp o) throws IncorrectOperation;
    ObjEmp mod(ObjEmp o) throws IncorrectOperation;
}
