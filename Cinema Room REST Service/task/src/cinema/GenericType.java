package cinema;

class GenericType<T> {

    private T t;

    public GenericType(){}

    public GenericType(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }
}