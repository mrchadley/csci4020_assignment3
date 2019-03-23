grammar Program;

@header {
  import java.util.*;
}

program returns [Expr code]
  : /* TO BE COMPLETED */
  ;

WHITESPACE : (' ' | '\t' )+ -> skip;
NL : ('\n' | '\r')+;
FLOAT : [0-9] ('.' [0-9]*);
INT : [0-9]+;
STRING : '"' (~('"'))* '"' ;
ID : ('a' .. 'z' | 'A' .. 'Z')+;
