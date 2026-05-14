package libreria;

public class ListaEnlazadaSimple {

    public Nodo cabeza;

    // ✅ Recorrer
    public void recorrer() {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.print(actual.dato + " -> ");
            actual = actual.siguiente;
        }
        System.out.println("null");
    }

    // ✅ Buscar
    public boolean buscar(int valor) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.dato == valor) return true;
            actual = actual.siguiente;
        }
        return false;
    }

    // ✅ Insertar al inicio
    public void insertarInicio(int dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
    }

    // ✅ Insertar al final
    public void insertarFinal(int dato) {
        Nodo nuevo = new Nodo(dato);

        if (cabeza == null) {
            cabeza = nuevo;
            return;
        }

        Nodo actual = cabeza;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }
        actual.siguiente = nuevo;
    }

    // ✅ Insertar en posición
    public void insertarEnPosicion(int dato, int pos) {
        if (pos == 0) {
            insertarInicio(dato);
            return;
        }

        Nodo nuevo = new Nodo(dato);
        Nodo actual = cabeza;
        int i = 0;

        while (actual != null && i < pos - 1) {
            actual = actual.siguiente;
            i++;
        }

        if (actual == null) return;

        nuevo.siguiente = actual.siguiente;
        actual.siguiente = nuevo;
    }

    // ✅ Eliminar al inicio
    public void eliminarInicio() {
        if (cabeza != null) {
            cabeza = cabeza.siguiente;
        }
    }

    // ✅ Eliminar por valor
    public void eliminar(int valor) {
        Nodo actual = cabeza, anterior = null;

        while (actual != null) {
            if (actual.dato == valor) {
                if (anterior == null) cabeza = actual.siguiente;
                else anterior.siguiente = actual.siguiente;
                return;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
    }

    // ✅ Ordenar (burbuja)
    public void ordenar() {
        if (cabeza == null) return;

        boolean cambio;
        do {
            cambio = false;
            Nodo actual = cabeza;

            while (actual.siguiente != null) {
                if (actual.dato > actual.siguiente.dato) {
                    int temp = actual.dato;
                    actual.dato = actual.siguiente.dato;
                    actual.siguiente.dato = temp;
                    cambio = true;
                }
                actual = actual.siguiente;
            }
        } while (cambio);
    }

    // ✅ Invertir lista
    public void invertir() {
        Nodo anterior = null;
        Nodo actual = cabeza, siguiente;

        while (actual != null) {
            siguiente = actual.siguiente;
            actual.siguiente = anterior;
            anterior = actual;
            actual = siguiente;
        }
        cabeza = anterior;
    }

    // ✅ Floyd (detectar ciclo)
    public boolean tieneCiclo() {
        Nodo lento = cabeza, rapido = cabeza;

        while (rapido != null && rapido.siguiente != null) {
            lento = lento.siguiente;
            rapido = rapido.siguiente.siguiente;
            if (lento == rapido) return true;
        }
        return false;
    }

    // ✅ Fusionar listas ordenadas
    public static ListaEnlazadaSimple fusionar(ListaEnlazadaSimple l1, ListaEnlazadaSimple l2) {
        ListaEnlazadaSimple r = new ListaEnlazadaSimple();
        Nodo n1 = l1.cabeza, n2 = l2.cabeza;

        while (n1 != null && n2 != null) {
            if (n1.dato < n2.dato) {
                r.insertarFinal(n1.dato);
                n1 = n1.siguiente;
            } else {
                r.insertarFinal(n2.dato);
                n2 = n2.siguiente;
            }
        }

        while (n1 != null) {
            r.insertarFinal(n1.dato);
            n1 = n1.siguiente;
        }

        while (n2 != null) {
            r.insertarFinal(n2.dato);
            n2 = n2.siguiente;
        }

        return r;
    }
}