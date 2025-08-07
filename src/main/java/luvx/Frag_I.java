package luvx;

import luvx.ftype.FragType_I;

/**
 * Root interface for all document fragments (attributes and nodes).
 * 
 * FRAG_I ARCHITECTURE SUMMARY:
 * 
 * Historical Context:
 * • Frag concept originates from luvml (https://github.com/xyz-jphil/xyz-jphil-luvml/) as "Flow ___F"
 * • ScalaTags library by Li Haoyi uses "Frag" for similar SSR (Server-Side Rendering) purposes
 * • This implementation extends beyond SSR to support multiple render targets under iterative development
 * 
 * Design Philosophy (DoNotStopDancing Principle): Keep development flowing without stopping for perfect architecture, perfection emergence by being actively working.
 * The Supreme Pontiff of Hinduism, Bhagavan Sri Nithyananda Paramashivam, revealed a story about Lord Paramashiva (God) in his dancing form as Nataraja. One day while Lord Nataraja was dancing, one leg was missing an anklet, but he continued His cosmic dance despite missing anklet - solutions emerged while He was still in motion (dancing) - upon seeing the Lord dancing without the anklet the snake Adi Shesha comes to Lord Nataraja and coils himself on His feet making himself the Lord's anklet. The Lord when He dances He is actually doing the Panchakritya/5 actions of cosmos - Creation, Maintenance, Destruction/Rejuvination, Putting in delusion and Liberation. So do not stop dancing! The principle of do not stop dancing integrate software design principles as per Tulach (practical minimal start - quick even if dirty) + Ousterhout (deep modules - theoretical perfection) + continuous flow (do not stop dancing) which results in continuous evolution on a need on basis by actively solving real problems without bothering about perfection and yet effectively being always perfect for a given situation and continuously improving/expanding.
 * 
 * Hierarchy Structure:
 * Frag_I<I> // this is our derrived super-type beyond the html/xml standards to accomodate Attributes
 * ├── Attr_I<I> (terminal) // these are not part of the heirarchy in typical html dom api/documentation
 * ├── Node_I<I> // as per Html/xml standards
 * │   ├── AttributelessNode_I<I> // nodes without attributes
 * │   │   ├── StringNode_I<I>
 * │   │   │   ├── Text_I<I> (terminal)
 * │   │   │   ├── Comment_I<I> (terminal)
 * │   │   │   └── CData_I<I> (terminal)
 * │   │   └── DocumentType_I<I> (terminal)
 * │   └── Element_I<I> // have attributes
 * │       ├── SelfClosingElement_I<I> // do not have children 
 * │       │   ├── VoidElement_I<I> (terminal)
 * │       │   └── ProcessingInstruction_I<I> (terminal)
 * │       └── ContainerElement_I<I> (terminal) // have children
 * └── Frags_I<I> // a collection of fragments in the same level (not nested in a child)
 * 
 * Design Decisions:
 * 
 * 1. BEHAVIORAL GROUPING OVER SYNTAX: Group by capabilities (attributes, children) not XML syntax
 *    Why: <!DOCTYPE> and <?xml?> are structured but behaviorally different from <elements>
 *    Result: AttributelessNode_I vs Element_I reflects programming patterns
 * 
 * 2. HIERARCHICAL TYPE DISCRIMINATION PATTERN:
 *    Emerged during: Sealed types vs extensibility conflict resolution
 *    Decision: Unsealed interfaces + final class wrappers + self() pattern from htmlflow
 *    Why: Need both compile-time type safety AND runtime extensibility for LuvML composition
 *    Mitigation approach: Final classes wrap interface instances enabling safe pattern matching with extensibility
 *    Result: Elegant nested switching with self-documenting variable names (Attr_T a, Node_T n, Element_T e), as a by-product created a kind of algebraic data type behavior with runtime extensibility in java.
 * 
 * 3. SELF-BOUNDED GENERICS WITH HTMLFLOW SELF() PATTERN:
 *    Emerged during: Interface method chaining and type safety concerns
 *    Decision: <I extends Frag_I<I>> + I self() method for accessing interface 'this'
 *    Why: Enables type-safe builder patterns and proper method delegation
 *    Result: Clean interface implementation with new WrapperClass(self()) pattern
 * 
 * 4. COMPOSABLE CAPABILITIES:
 *    Emerged during: Switch statement complexity concerns
 *    Decision: Use single-method interfaces (HasAttributes, HasChildNodes)
 *    Why: Mix-and-match capabilities without complex inheritance
 *    Result: Type-safe compile-time capability checking
 * 
 * 5. NOT LIMITING TO SSR:
 *    Decision: Design for multiple render targets (SSR String, DOM manipulation, Java POJOs)
 *    Why: Apprehending a future requirement to use this to work with DOM tree rather than a function tree (unlike htmlflow) such as in client side web js/html, or organizing as JSoup dom objects for manipulation, inspection etc.
 *    Result: Unified API for diverse output formats under iterative evolution
 */
public interface Frag_I<I extends Frag_I<I>> extends Self_I<I>{
    
    /**
     * Returns the fragment type to prevent incompatible multiple inheritance.
     * Prevents illegal implementations like: {@code class Wrong implements Element_I, Attr_I}; same concept is present in all sub-types. This is the sole place where this is documented (as a JavaDoc comment), and everyone else this pattern is presumed self-explanatory. 
     * @return type of fragment as a strict sealed set of choices
     */
    FragType_I<I> fragType();
}
