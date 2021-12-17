/* Generated By:JavaCC: Do not edit this line. rowJsonParser.java */
package vlang.interpreter.parsers.rowJsonParser;
import java.util.ArrayList;
import org.json.JSONArray;import org.json.JSONObject;import vlang.globalSetting;
import java.io.*;
import vlang.interpreter.registry;
import vlang.interpreter.parser;

public class rowJsonParser implements rowJsonParserConstants {
    private  static int errorNum=0;
    private static final JSONObject entrySymbol=new JSONObject("{\"#\":0}");

    public rowJsonParser(){

    }

    public  JSONObject parse(String inPath)throws IOException {
        errorNum=0;
        globalSetting.init();
        rowJsonParser parser= new rowJsonParser(new InputStreamReader(new FileInputStream(inPath),"UTF-8"));
        JSONObject jsonObject = null;
        try {
             jsonObject = parser.start();
             } catch (ParseException e) {
                   e.printStackTrace();
                   };
        if(errorNum>0){
            globalSetting.log.warning("Parse Fail : "+errorNum+" errors detected.");
            jsonObject=null;
        }
        else
            globalSetting.log.warning("Parse Success.");
        return jsonObject;
    }

  static final public JSONObject start() throws ParseException, NumberFormatException {
    Token Step;
    JSONArray son;
    JSONObject ans=new JSONObject();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case STEP:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      jj_consume_token(STEP);
      Step = jj_consume_token(IDENTIFIER);
      son = rStep();
            if(ans.has(Step.image)){
                globalSetting.log.warning("Step "+Step.image+" has already defined.\n");
                errorNum++;
            }

            if(son.get(0).equals(entrySymbol)){
                son.remove(0);
                if(ans.has(registry.entry)){
                globalSetting.log.warning("Entry has already defined as "+ans.getString(registry.entry)+" but "+Step.image);
                errorNum++;
                }
                else
                   ans.put(registry.entry,Step.image);
                }
            globalSetting.log.info("Finish parse Step "+Step.image);
            ans.put(Step.image,son);
    }
    jj_consume_token(0);
    if(!ans.has(registry.entry)){
                    globalSetting.log.warning("Entry is not defined");
                    errorNum++;
                }
    {if (true) return ans;}
    throw new Error("Missing return statement in function");
  }

  static final public JSONArray rStep() throws ParseException {
    Token Func = null;
    JSONArray ans=new JSONArray();
    JSONObject son=new JSONObject();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ENTRY:
      jj_consume_token(ENTRY);
        ans.put( entrySymbol);
      break;
    default:
      jj_la1[1] = jj_gen;
      ;
    }
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case FUNCTION:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_2;
      }
      Func = jj_consume_token(FUNCTION);
      son = rFunc(Func.image);
        ans.put(son);
    }
     {if (true) return ans;}
    throw new Error("Missing return statement in function");
  }

  static final public JSONObject rFunc(String func) throws ParseException {
    JSONObject ans;
    Token now=null;
     ArrayList<String> tokens=new ArrayList<String>();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NUMBER:
      case IDENTIFIER:
      case STRING:
        ;
        break;
      default:
        jj_la1[3] = jj_gen;
        break label_3;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NUMBER:
        now = jj_consume_token(NUMBER);
        break;
      case STRING:
        now = jj_consume_token(STRING);
        break;
      case IDENTIFIER:
        now = jj_consume_token(IDENTIFIER);
        break;
      default:
        jj_la1[4] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
          tokens.add(now.image);
    }
        ans=registry.func.get(func).buildJson(tokens).put(registry.function,func);
        {if (true) return ans;}
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public rowJsonParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[5];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x80,0x100,0x200,0x1c00,0x1c00,};
   }

  /** Constructor with InputStream. */
  public rowJsonParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public rowJsonParser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new rowJsonParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 5; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 5; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public rowJsonParser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new rowJsonParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 5; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 5; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public rowJsonParser(rowJsonParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 5; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(rowJsonParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 5; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List jj_expentries = new java.util.ArrayList();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[13];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 5; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 13; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}