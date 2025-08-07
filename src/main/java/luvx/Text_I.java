package luvx;

import luvx.ftype.Text_T;

/**
 * Interface for plain text nodes.
 */
public interface Text_I<I extends Text_I<I>> extends StringNode_I<I> {
    
    @Override
    default Text_T<I> stringNodeType() {
        return new Text_T(self());
    }
}
