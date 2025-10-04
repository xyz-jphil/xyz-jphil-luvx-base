package luvx;

import luvx.composable.HasEscapableTextContent;
import luvx.ftype.Text_T;

/**
 * Interface for plain text nodes.
 * Text nodes contain plain text that needs HTML character escaping during rendering.
 */
public interface Text_I<I extends Text_I<I>>
    extends StringNode_I<I>, HasEscapableTextContent<I> {

    /**
     * Returns the text content of this text node.
     * This text will be escaped during rendering (< becomes &amp;lt;, etc.).
     */
    String text();

    /**
     * Generic accessor for escapable text content.
     * Delegates to text() for consistency with HasEscapableTextContent interface.
     */
    @Override
    default String escapableTextContent() {
        return text();
    }

    @Override
    default Text_T<I> stringNodeType() {
        return new Text_T(self());
    }
}
