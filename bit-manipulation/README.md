# Bit manipulation

**Basics**

At the heart of bit manipulation are the bit-wise operators & \(and\), \| \(or\), ~ \(not\) and ^ \(exclusive-or, xor\) and shift operators a &lt;&lt; b and a &gt;&gt; b.

Set union A \| B

Set intersection A & B

Set subtraction A & ~B

Set negation ALL\_BITS ^ A or ~A

Set bit A \|= 1 &lt;&lt; bit

Clear bit A &= ~\(1 &lt;&lt; bit\)

Test bit \(A & 1 &lt;&lt; bit\) != 0

**Extract last bit A&-A or A&~\(A-1\) or x^\(x&\(x-1\)\)     补码\(-A\)= 反码+1**

**Remove last bit 1: A&\(A-1\)**

Get all 1-bits ~0

**XOR operation.  a^b^b =a**



**补码= 反码+1**

"~" NOT operation, for example, ~2\(0010\) =&gt; -3 \(1101\) what??? Don't get frustrated here. It's called two's complement.

1110 is -2, which is ~2 + 1, ~0010 =&gt; 1101, 1101 + 1 = 1110 =&gt; 2



常用技巧：

（1）n & （n-1）能够消灭n中最右侧的一个1。

（2） 右移：除以2， 左移：乘以2。

（3）异或性质：交换律，0^a=a, a^a=0;

（3）将常用字符、数字等均转为按位运算，可以节约空间。

\*\*\*\*

**^  XOR**

e.g. 101

        111

        010



**&gt;&gt;** shift divide by 2

**&gt;&gt;&gt;** unsigned shift rght

[https://stackoverflow.com/questions/19058859/what-does-mean-in-java/19058871](https://stackoverflow.com/questions/19058859/what-does-mean-in-java/19058871)

