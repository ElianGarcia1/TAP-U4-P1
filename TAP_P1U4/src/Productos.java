
class Productos {
    String DESCRIPCION;
    int ID, EXISTENCIA, PRECIO;
    
    public Productos(){
        
    }
    
    public Productos(String d, int e, int p, int ID){
        this.ID=ID;
        DESCRIPCION = d;
        EXISTENCIA = e;
        PRECIO = p;
    }
    public Productos(String d, int e, int p){
        DESCRIPCION = d;
        EXISTENCIA = e;
        PRECIO = p;
    }


}
