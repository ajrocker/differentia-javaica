Missing features: possibility of comparing java source codes where syntactic elements were reordered, but this reordering does not affect semantic structure. Example: reordering of class fields declarations.

Another problem occurs when specific declarations are not grouped within one source - for example class fields declaration put at the beginning and at the end of the class.

It should be possible to regroup such children in AST tree, sort them by names, and then compare. But is that really true that fields could be reordered without side effects? What about associated initializer blocks?