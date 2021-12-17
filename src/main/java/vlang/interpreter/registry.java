package vlang.interpreter;

import vlang.interpreter.functions.askManualService;
import vlang.interpreter.functions.listen;
import vlang.interpreter.functions.speak;
import vlang.interpreter.functions.exit;

import java.util.HashMap;

public abstract class registry {


    public final static String entry="@entry";
    public final static String function="function";
    public final static String goTo="goto";
    public final static String goOn="goon";
    public final static String exit="exit";
    public final static String param="param";
    public final static String silenceLimt="time";
    public final static String listenDefault="$@Default";
    public final static String listenSilence="$@Silence";
    public final static String listenFailure="$@failure";
    public final static String nlpFailure="$@nlpFailure";


    public final static HashMap<String,?extends function> func=new HashMap<>(){{
               put(listen.name,new listen());
               put(askManualService.name,new askManualService());
               put(vlang.interpreter.functions.exit.name,new exit());
               put(speak.name,new speak());
    }};
}
