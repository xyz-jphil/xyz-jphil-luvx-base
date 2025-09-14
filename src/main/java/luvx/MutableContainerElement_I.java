package luvx;

public interface MutableContainerElement_I<T extends MutableContainerElement_I<T>> extends ContainerElement_I<T>{
    T ____(Iterable<Frag_I<?>> fragments);
    T ____(Frag_I<?>... fragments);
    T ____(String ... fragments);
    
    T addContent(Iterable<Frag_I<?>> fragments);
    T addContent(Frag_I<?>... fragments);
    T addContent(String ... fragments);
}
