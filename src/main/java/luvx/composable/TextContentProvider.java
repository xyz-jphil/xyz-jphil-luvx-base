package luvx.composable;

import luvx.ContainerElement_I;
import luvx.Node_I;
import luvx.ftype.AttributelessNode_T;
import luvx.ftype.CData_T;
import luvx.ftype.Comment_T;
import luvx.ftype.ContainerElement_T;
import luvx.ftype.Doctype_T;
import luvx.ftype.Element_T;
import luvx.ftype.ProcessingInstruction_T;
import luvx.ftype.SelfClosingElement_T;
import luvx.ftype.StringNode_T;
import luvx.ftype.Text_T;
import luvx.ftype.VoidElement_T;

public interface TextContentProvider<I extends ContainerElement_I<I>> {
    I self();
    default String textContent(){
        var sb = new StringBuilder();
        plainTextFrom(self(), sb);
        return sb.toString();
    }

    public static void plainTextFrom(Node_I n, StringBuilder sb){
        switch (n.nodeType()) {
            case AttributelessNode_T a -> {
                switch (a.attributelessNodeType()) {
                    case StringNode_T s ->{
                        switch (s.stringNodeType()) {
                            case Text_T t -> 
                                sb.append(t.text().textContent());
                            case Comment_T c->{}
                            case CData_T cd->{}
                        }
                    }
                    case Doctype_T d -> {}
                }
            }
            case Element_T a -> {
                switch (a.elementType()) {
                    case ContainerElement_T c -> {
                        for (var childNodes : c.containerElement().childNodes()) {
                            plainTextFrom((Node_I)childNodes, sb);
                        }
                    }
                    case SelfClosingElement_T s -> {
                        switch (s.selfClosingElementType()) {
                            case ProcessingInstruction_T p -> {} 
                            case VoidElement_T v -> {}
                        }
                    }
                }
            }

        }
    }
    

    
}
