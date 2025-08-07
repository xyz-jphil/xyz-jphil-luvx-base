package luvx.composable;

import java.util.Map;
import java.util.Set;

/**
 * Composable interface for nodes that can have attributes
 */
public interface HasAttributes<I extends HasAttributes<I>> {
    
    String attr(String name);
    
    Map<String, String> attributes();
    
    default Set<String> attributeNames() {
        return attributes().keySet();
    }
    
    default boolean hasAttribute(String name) {
        return attributes().containsKey(name);
    }
}