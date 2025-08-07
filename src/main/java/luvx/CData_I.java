package luvx;

import luvx.ftype.CData_T;

/**
 * Interface for CDATA sections.
 * The content of a CDATA section is not parsed by the XML parser.
 */
public interface CData_I<I extends CData_I<I>> extends StringNode_I<I> {

    @Override
    default CData_T<I> stringNodeType() {
        return new CData_T<>(self());
    }
}
