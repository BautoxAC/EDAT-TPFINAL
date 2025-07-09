package Estructuras.Especificas.Diccionario;

import General.Nodo;

public class Diccionario {
    
    private NodoAVLDicc raiz;

    public Diccionario () {

        this.raiz = null;

    }

    public boolean insertar(Object [] par) {

        boolean[] exito = { false };

        if (this.raiz == null) {
            this.raiz = new NodoAVLDicc (par[1],par[2]);
            exito[0] = true;
        } else {
            this.raiz = insertarRec(this.raiz, par, exito);
        }
        return exito[0];
    }

    private NodoAVLDicc insertarRec(NodoAVLDicc n, Object [] par, boolean[] exito) {
        Comparable elem = (Comparable) par[1];
        int comparar = elem.compareTo(n.getClave());
        if (n != null) {
            if (comparar != 0) {
                if (comparar < 0) {
                    if (n.getHijoIzquierdo() != null) {
                        n.setHijoIzquierdo(insertarRec(n.getHijoIzquierdo(), par, exito));
                    } else {
                        n.setHijoIzquierdo(new NodoAVLDicc(par[1],par[2]));
                        exito[0] = true;
                    }
                } else {
                    if (n.getHijoDerecho() != null) {
                        n.setHijoDerecho(insertarRec(n.getHijoDerecho(), par, exito));
                    } else {
                        n.setHijoDerecho(new NodoAVLDicc(par[1],par[2]));
                        exito[0] = true;
                    }
                }
            }
        }
        if (exito[0]) {
            n.recalcularAltura();
            int balance;
            balance = calcularBalance(n);
            System.out.println(n.getElem() + " balance: " + balance + " elemIn: " + elem + " altura: " + n.getAltura());
            if (balance > 1) {
                // desabalaceado por izq entonces balanceo por izq
                // rotacion a der

                int balanceHijo = calcularBalance(n.getIzquierdo());
                if (balanceHijo < 0) {
                    n.setIzquierdo(balancearDer(n.getIzquierdo()));

                }
                n = balancearIzq(n);

            }
            if (balance < -1) {
                // desabalaceado por der entonces balanceo por der
                // roto a IZQ
                int balanceHijo = calcularBalance(n.getDerecho());
                System.out.println(balanceHijo);
                if (balanceHijo > 0) {
                    n.setDerecho(balancearIzq(n.getDerecho()));
                }
                n = balancearDer(n);
            }
        }
        return n;
    }



}
