import java.util.Scanner;
public class boyer { 
private static int[] makeCharTable(char[] p) {
final int ALPHABET_SIZE = 1000;
int[] table = new int[ALPHABET_SIZE];
for (int i = 0; i < table.length; ++i) {
table[i] = p.length;
}         
for (int i = 0; i < p.length - 1; ++i) {
table[p[i]] = p.length - 1 - i;
}
return table;
}
private static int[] makeOffsetTable(char[] p) {
int[] table = new int[p.length];
int last = p.length;
for (int i = p.length - 1; i >= 0; --i) {
if (isPrefix(p, i + 1)) {
last = i + 1;
}
table[p.length - 1 - i] = last - i + p.length - 1;
}
for (int i = 0; i < p.length - 1; ++i) {
int slen = suffixLength(p, i);
table[slen] = p.length - 1 - i + slen;
}
return table;
}
private static boolean isPrefix(char[] pp, int p) {
for (int i = p, j = 0; i < pp.length; ++i, ++j) {
if (pp[i] != pp[j]) {
return false;
} 
}
return true;
}
private static int suffixLength(char[] pp, int p) {
int len = 0;
for (int i = p, j = pp.length - 1;i >= 0 && pp[i] == pp[j]; --i, --j) {
len += 1;
}
return len;
}
public static void main(String[] args) {
System.out.println("Enter String :");
Scanner sc=new Scanner(System.in);
String ss=sc.next();
char []s=ss.toCharArray();
System.out.println("Enter Pattern :"); 
String pp=sc.next();
char []p=pp.toCharArray();
if (p.length == 0) {
System.out.println("Index:"+"0");
}
int charTable[] = makeCharTable(p);
System.out.println("Character table:");
for(char i:p)
{
System.out.println(charTable[i]+" ");
}
int offsetTable[] = makeOffsetTable(p);
System.out.println("offset table:");
for(int i:offsetTable)         {
System.out.println(i+" ");
}
for (int i = p.length - 1, j; i < s.length;) {
for (j = p.length - 1; p[j] == s[i]; --i, --j) {
if (j == 0) {
System.out.println("Index:" +i);
System.exit(0);
}
}
i += Math.max(offsetTable[p.length - 1 - j], charTable[s[i]]);
}
System.out.println("Patten not found! ");
}
}
