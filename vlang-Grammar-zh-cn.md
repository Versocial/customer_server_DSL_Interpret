# vlang 

## vlang 语言简介：

vlang是一门用于编写客服机器人的领域专用语言。

你可以使用vlang语言轻松地编写属于自己的客服机器人。

## vlang 语法：

1. 你需要编写的vlang语言源文件由多个无序步骤(Step)构成。

   这里**无序**指:

   ​			入口步骤(Step)必须在代码中显式指明。

   ​			每个步骤(Step)的后续步骤(Step)必须由跳转指定(若有后续步骤)。

   也即源文件中的各个步骤(Step)之间可以任意交换位置而不改变源文件的语义。

2. 每个步骤(Step)包含多个有序的函数(Function)构成。

   这里**有序**指每个步骤(Step)执行时组成该步骤的函数(Function)依照文本顺序依次执行。

3. 源文件中必须有且只有一个步骤(Step)有入口标记。

   入口标记表示该步骤(Step)为执行时的第一个步骤(Step)。

4. 步骤(Step)格式。

   Step ID [@entry]

   ​	函数(Function)

   ​	[函数(Function)]*

   示例代码：

   ```
   Step thanks
   Speak "感谢您的来电 再见"
   Exit
   ```

   

5. 函数(Function)格式和功能。

| 函数名 | 格式                               | 功能     | 代码示例                     |
| ------ | ---------------------------------- | -------- | ---------------------------- |
| Speak  | Speak {[$用户信息变量]\|[字符串]}* | 输出句子 | Speak "Welcome, " $name " !" |
| Listen | 见表格后                           | 听取输入 | 见表格后                     |
| Exit   | Exit                               | 退出对话 | Exit                         |

Listen格式：

​	Listen 最长沉默时间(正整数，单位秒)

​	[Branch 字符串 步骤(Step)名]*

​	[Silence 步骤(Step)名]

​	[Default 步骤(Step)名]

Listen 代码示例：

```
Listen 10
Branch "投诉" complainProc
Branch "账单" billProc
Silence silenceProc
Default defaultProc
```

注：最长沉默时间（这里用t指代）的含义：当且仅当t秒时间内没有新的输入时输入结束。

6. vlang支持源文件中的注释。

注释格式：以#开头，到行尾结束。

示例代码：

```
# 这是一行注释。
# 这是另一行注释。
```



7. 源文件示例

```
Step welcome @entry
Speak $name  " 您好请问有什么可以帮您?"
Listen 10
Branch "投诉" complainProc
Branch "账单" billProc
Silence silenceProc
Default thanks

Step complainProc
Speak "您的意见是我们改进工作的动力 请问您还有什么补充?"
Listen 10
Branch "别挂" complainProc
Default thanks

Step thanks
Speak "感谢您的来电 再见"
Exit

Step billProc
Speak "您的本月账单是" $amount "元 感谢您的来电 再见"
Exit

Step silenceProc
Speak "听不清 请您大声一点可以吗"
Listen 10
Branch "投诉" complainProc
Branch "账单" billProc
Silence silenceProc
```



## vlang 使用流程：

1. 编写源代码。

   源代码语法见上文。

2. 语法分析生成中间脚本。

3. 执行。

4. 默认输入：弹出的JFrame内文本框。

5. 默认输出：stdout。

