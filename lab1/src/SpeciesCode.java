public class SpeciesCode {
    //定义种别码
    // 26为标识符种别码
    protected static final int ID = 26;

    // 30为常量种别码
    protected static final int NUM = 30;

    // 31-40为运算符种别码
    protected static final int AS = 31; // =
    private static final int EQ = 32; // ==
    private static final int GT = 33; // >
    private static final int LT = 34; // <
    private static final int GE = 35; // >=
    private static final int LE = 36; // <=
    private static final int ADD = 37; // +
    private static final int SUB = 38; // -
    private static final int MUL = 39; // *
    protected static final int DIV = 40; // /

    // 41-49为界限符种别码
    protected static final int LP = 41; // (
    private static final int RP = 42; // )
    private static final int LBT = 43; // [
    private static final int RBT = 44; // ]
    private static final int LBS = 45; // {
    private static final int RBS = 46; // }
    private static final int COM = 47; // ,
    private static final int COL = 48; // :
    protected static final int SEM = 49; // ;

    // -1为无法识别的字符标志码
    protected static final int ERROR = -1;
}
