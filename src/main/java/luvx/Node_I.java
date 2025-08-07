package luvx;

import luvx.ftype.*;

/**
 * Base interface for all document nodes. 
 * Unsealed to enable extensibility while maintaining type discrimination through nodeType().
 */
public interface Node_I<I extends Node_I<I>> extends Frag_I<I> {
    
    NodeType_I<I> nodeType();
    
    /**
     * Returns the markup rendering behavior for AI-optimized output.
     * NO DEFAULT IMPLEMENTATION - implementation classes decide behavior.
     */
    MarkupRenderingBehavior_I markupRenderingBehavior();
    
    @Override
    default Node_T<I> fragType() {
        return new Node_T(self());
    }
}
