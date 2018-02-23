# linux 基础

 - 常用命令
 
   - 输入/输出重定向（ <、 >）
     1. 将 hello.out 的输出重定向到文件
     - 答：
          ```
          1. cat >hello.c
          2. #include<stdio.h>
             void main(){
                printf("Hello World\n");
             }
          3. gcc hello.c -o hello.out
          4. hello.out>out.txt
          ```
   - 创建目录
      1. 请在用户工作目录下新建一个名为 vi-test 的目录
      2. 进入 vi-test 这个目录当中
      3. 将示例文件 ex3_sample.txt 传送到 vi-test 这个目录当中
        - 答：
          ```
           1. mkdir vi-test
           2. cd vi-test  
           3. mv ex3_sample.txt vi-test
          ```                             
    - 熟悉vi
       1. 在 vi 中设置行号
       2. 将 50 到 100 行之间的”man”改为”MAN”
       3. 修改完成之后，突然反悔了，要全部复原，有哪些方法？
       4. 复制 65 到 73 这九行的内容,并且粘贴到最后一行之后.
       5. 21 到 42 行之间所有批注我不要了，要如何删除？
       6. 将这个文件另存成名为 man.test.config 的文件名
       7. 去到第 27 行，并且删除 15 个字符.
         - 答：
           ```
            1. :set nu
            2. :50,100s/man/MAN/gc
            3. 按u
            4. 65G 9yy G p
            5. 21G 22dd
            6. :w man.test.config
            7. 27G 15x
           ```  
    - 前台与后台切换
    
        |序号         |	命令或操作|选项与示例 |	说明|
        | :--------  | :-----    | :----  |:---- |
        |1           |&          |	vi hello.c & 	|程序后台运行|
        |2           |	CRTL+z   | 	暂停前台程序，切换到提示符界面||	
        |3 	         |bg 	     |bg %[任务号] |	将“任务号”的程序后台运行|
        | 4          |	fg       |	fg %[任务号] |	将“任务号”的后台程序切换到前台|
        |5	         |jobs     	 |jobs -l	|查看所有在前台和后台运行的程序。任务号在[ ] 中|
        |6           |kill 	     |kill %[任务号] |	终止任务|
        |7           |	ps       | 	显示当前进程| |                     
                      	
    - 熟悉vi
       1. 使用 echo 命令和重定位创建 hello.c 文件
       2. 在当前用户目录创建目录 test
       3. 将/etc/passwd 复制到 test 目录
       4. 编辑多文件vi passwd result.txt,将 passwd 文件中含“ root”字符串的所有行复制到新文件 result.txt中
       5. 21 到 42 行之间所有批注我不要了，要如何删除？
       6. 将这个文件另存成名为 man.test.config 的文件名
       7. 去到第 27 行，并且删除 15 个字符.
         - 答：
           ```
            1. echo "hello world!">hello.c
            2. mkdir test
            3. cp /etc/passwd test
            4. 1 vi passwd result.txt 2  grep root passwd > result.txt
            5. 21G 22dd
            6. :w man.test.config
            7. 27G 15x
           ```