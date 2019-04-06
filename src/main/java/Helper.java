import java.util.*;

class Program implements Code 
{
    List<Code> list;
    
    public double eval(Map<String, Double> scope) 
    {
        for(Code c : this.list) 
        {
            c.eval(scope);
        }
        return 0.0;
    }

    static Program empty() 
    {
        Program p = new Program();
        p.list = new ArrayList<Code>();
        return p;
    }
    
    static void add(Code p, Code c) 
    {
        ((Program)p).list.add(c);
    }
}

class Print implements Code 
{
    List<StringCode> list;
    public double eval(Map<String, Double> scope) 
    {
        for(StringCode s : this.list) 
        {
            System.out.print(s.eval(scope) + " ");
        }
        System.out.println();
        return 0.0;
    }

    static Print empty() 
    {
        Print x = new Print();
        x.list = new ArrayList<StringCode>();
        return x;
    }

    static void add(Code x, StringCode c) 
    {
        ((Print)x).list.add(c);
    }

    static Code expr(Code c) 
    {
		return new Code () 
        {
			public double eval(Map<String, Double> scope) {
				System.out.print("> ");
				System.out.println(c.eval(scope));
				return 0.0;
			}
		};
	}
}

class Output 
{
    static StringCode string(String text) 
    {
        return new StringCode() 
        {
            public String eval(Map<String, Double> scope) 
            {
                return text.substring(1, text.length()-1);
            }
        };
    }
    static StringCode expr(Code code) 
    {
        return new StringCode() 
        {
            public String eval(Map<String, Double> scope) 
            {
                return (new Double(code.eval(scope))).toString();
            }
        };
    }
    static StringCode expr(Code code, String precision) 
    {
        return new StringCode() 
        {
            public String eval(Map<String, Double> scope) 
            {
                return String.format("%." + precision + "f", code.eval(scope));
            }
        };
    }
}

class Assignment 
{
    static Code make(String var, Code code) 
    {
        return new Code() 
        {
            public double eval(Map<String, Double> scope) 
            {
                double result = code.eval(scope);
                scope.put(var, result);
                return result;
            }
        };
    }
}

class Expr 
{
    static Code pow(Code l, Code r) 
    {
        return new Code() {
            public double eval(Map<String, Double> scope) 
            {
                return Math.pow(l.eval(scope), r.eval(scope));
            }
        };
    }
    static Code mul(Code l, Code r) 
    {
        return new Code() 
        {
            public double eval(Map<String, Double> scope) 
            {
                return l.eval(scope) * r.eval(scope);
            }
        };
    }
    static Code div(Code l, Code r)
    {
        return new Code() 
        {
            public double eval(Map<String, Double> scope) 
            {
                return l.eval(scope) / r.eval(scope);
            }
        };
    }
    static Code add(Code l, Code r) 
    {
        return new Code() 
        {
            public double eval(Map<String, Double> scope) 
            {
                return l.eval(scope) + r.eval(scope);
            }
        };
    }
    static Code sub(Code l, Code r)
    {
        return new Code() 
        {
            public double eval(Map<String, Double> scope) 
            {
                return l.eval(scope) - r.eval(scope);
            }
        };
    }
    static Code neg(Code c) 
    {
        return new Code() 
        {
            public double eval(Map<String, Double> scope) 
            {
                return -c.eval(scope);
            }
        };
    }
    static Code var(String v) 
    {
        return new Code() 
        {
            public double eval(Map<String, Double> scope) 
            {
                if(scope.containsKey(v)) return scope.get(v);
                else return 0.0;
            }
        };
    }
}

class Literal 
{
    static Code num(String text) 
    {
        return new Code() 
        {
            public double eval(Map<String, Double> scope) 
            {
                return Double.parseDouble(text);
            }
        };
    }
}


class Cond 
{
	static Code lt(Code e1, Code e2) 
    {
		return new Code() 
        {
			public double eval (Map<String, Double> scope) 
            {
				double v1 = e1.eval(scope);
				double v2 = e2.eval(scope);
				
				if(v1 < v2) return 1.0; //true
				else return -1.0;       //false
			}
		};
	}
	static Code eq(Code e1, Code e2) 
    {
		return new Code() 
        {
			public double eval (Map<String, Double> scope) 
            {
				double v1 = e1.eval(scope);
				double v2 = e2.eval(scope);
				
				if(v1 == v2) return 1.0;    //true
				else return -1.0;           //false
			}
		};
	}
	static Code gt(Code e1, Code e2) 
    {
		return new Code() 
        {
			public double eval (Map<String, Double> scope) 
            {
				double v1 = e1.eval(scope);
				double v2 = e2.eval(scope);
				
				if(v1 > v2) return 1.0; //true
				else return -1.0;       //false
			}
		};
	}
}

class Branch implements Code 
{
	Code cond;
	Code ifCode;
	Code elseCode;

	public double eval(Map<String,Double> scope) 
    {
		double v = 0.0;
		if(this.cond.eval(scope) > 0) v = this.ifCode.eval(scope);
		else if(this.elseCode != null) v = this.elseCode.eval(scope);
	    return v;
	}
}

class WhileLoop implements Code 
{
	Code cond;
	Code body;
	
	public double eval(Map<String, Double> scope) 
    {
		while(cond.eval(scope) > 0)
			body.eval(scope);
		return 0.0;
	}
}