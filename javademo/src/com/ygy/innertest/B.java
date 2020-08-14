package com.ygy.innertest;

public class B {

    private U[] arrayU;

    U[] save(U u){
        if(arrayU == null){
            arrayU = new U[1];
        }
        arrayU[0]=u;
        return arrayU;
    }

    void funeach(U[] arrayU){
        for (U u:arrayU) {
            u.funU1();
            u.funU2();
            u.funU3();
        }
    }
}
