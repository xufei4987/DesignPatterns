# 重构
## 重构的目的：为什么要重构（why）
重构的定义：重构是一种对软件内部结构的改善，目的是在不改变软件的可见行为的情况下，使其更易理解，修改成本更低

在保持功能不变的前提下，利用设计思想、原则、模式、编程规范等理论来优化代码，修改设计上的不足，提高代码质量

## 重构的对象：到底重构什么（what）
- 大型重构指的是对顶层代码设计的重构，包括：系统、模块、代码结构、类与类之间的关系等的重构，重构的手段有：分层、模块化、解耦、抽象可复用组件等等
- 小型重构指的是对代码细节的重构，主要是针对类、函数、变量等代码级别的重构，比如规范命名、规范注释、消除超大类或函数、提取重复代码等等

## 重构的时机：什么时候重构（when）
提倡的重构策略是持续重构，把持续重构也作为开发的一部分，成为一种开发习惯

## 重构的方法：又该如何重构（how）
- 在进行大型重构的时候，我们要提前做好完善的重构计划，有条不紊地分阶段来进行。每个阶段完成一小部分代码的重构，然后提交、测试、运行，发现没有问题之后，再继续进行下一阶段的重构，保证代码仓库中的代码一直处于可运行、逻辑正确的状态
- 小规模低层次的重构，因为影响范围小，改动耗时短，所以，只要你愿意并且有时间，随时都可以去做。实际上，除了人工去发现低层次的质量问题，我们还可以借助很多成熟的静态代码分析工具，来自动发现代码中的问题，然后针对性地进行重构优化
- 保持代码质量最好的方法还是打造一种好的技术氛围，以此来驱动大家主动去关注代码质量，持续重构代码

## 什么是单元测试
单元测试由研发工程师自己来编写，用来测试自己写的代码的正确性。单元测试的测试对象是类或者函数，用来测试一个类和函数是否都按照预期的逻辑执行，这是代码层级的测试。

## 为什么要写单元测试
- 单元测试能有效地帮你发现代码中的bug
- 写单元测试能帮你发现代码设计上的问题
- 单元测试是对集成测试的有力补充
- 写单元测试的过程本身就是代码重构的过程
- 阅读单元测试能帮助你快速熟悉代码
- 单元测试是 TDD 可落地执行的改进方案

## 如何编写单元测试？
1. 写单元测试真的是件很耗时的事情吗？ 写的过程很繁琐，但并不是很耗时
2. 对单元测试的代码质量有什么要求吗？ 单元测试代码的质量可以放低一些要求
3. 单元测试只要覆盖率高就够了吗？  过度关注单元测试的覆盖率会导致写很多没有必要的测试代码，测试覆盖率在 60～70% 即可上线
4. 写单元测试需要了解代码的实现逻辑吗？  单元测试不要依赖被测试函数的具体实现逻辑，它只关心被测函数实现了什么功能
5. 如何选择单元测试框架？ 单元测试框架无法测试，多半是因为代码的可测试性不好

## 如何写出可测试性的代码
依赖注入是编写可测试性代码的最有效手段。通过依赖注入，我们在编写单元测试的时候，可以通过 mock 的方法解依赖外部服务，这也是我们在编写单元测试的过程中最有技术挑战的地方
1. 不要在内部new依赖，可以通过依赖注入的方式
2. 尽可能不要使用单例类，如果一定要使用，可以通过封装单例类来使用
3. 对未决行为逻辑重新封装

## 常见的测试不友好的代码
- 代码中包含未决行为逻辑
- 滥用可变全局变量
- 滥用静态方法
- 使用复杂的继承关系
- 高度耦合的代码

## 改善代码质量的20条编程规范
1. 关于命名
- 命名的关键是能准确达意。对于不同作用域的命名，我们可以适当地选择不同的长度。
- 我们可以借助类的信息来简化属性、函数的命名，利用函数的信息来简化函数参数的命名。
- 命名要可读、可搜索。不要使用生僻的、不好读的英文单词来命名。命名要符合项目的统一规范，也不要用些反直觉的命名。
- 接口有两种命名方式：一种是在接口中带前缀“I”；另一种是在接口的实现类中带后缀“Impl”。对于抽象类的命名，也有两种方式，一种是带上前缀“Abstract”，一种是不带前缀。这两种命名方式都可以，关键是要在项目中统一。
2. 关于注释
- 注释的内容主要包含这样三个方面：做什么、为什么、怎么做。对于一些复杂的类和接口，我们可能还需要写明“如何用”。
- 类和函数一定要写注释，而且要写得尽可能全面详细。函数内部的注释要相对少一些，一般都是靠好的命名、提炼函数、解释性变量、总结性注释来提高代码可读性。
3. 关于代码风格
- 函数、类多大才合适？函数的代码行数不要超过一屏幕的大小，比如 50 行。类的大小限制比较难确定。
- 一行代码多长最合适？最好不要超过 IDE 的显示宽度。当然，也不能太小，否则会导致很多稍微长点的语句被折成两行，也会影响到代码的整洁，不利于阅读。
- 善用空行分割单元块。对于比较长的函数，为了让逻辑更加清晰，可以使用空行来分割各个代码块。
- 四格缩进还是两格缩进？我个人比较推荐使用两格缩进，这样可以节省空间，尤其是在代码嵌套层次比较深的情况下。不管是用两格缩进还是四格缩进，一定不要用 tab 键缩进。
- 大括号是否要另起一行？将大括号放到跟上一条语句同一行，可以节省代码行数。但是将大括号另起新的一行的方式，左右括号可以垂直对齐，哪些代码属于哪一个代码块，更加一目了然。
- 类中成员怎么排列？在 Google Java 编程规范中，依赖类按照字母序从小到大排列。类中先写成员变量后写函数。成员变量之间或函数之间，先写静态成员变量或函数，后写普通变量或函数，并且按照作用域大小依次排列。
4. 关于编码技巧
- 将复杂的逻辑提炼拆分成函数和类。
- 通过拆分成多个函数或将参数封装为对象的方式，来处理参数过多的情况。
- 函数中不要使用参数来做代码执行逻辑的控制。
- 函数设计要职责单一。
- 移除过深的嵌套层次，方法包括：去掉多余的 if 或 else 语句，使用 continue、break、return 关键字提前退出嵌套，调整执行顺序来减少嵌套，将部分嵌套逻辑抽象成函数。
- 用字面常量取代魔法数。
- 用解释性变量来解释复杂表达式，以此提高代码可读性。
5. 统一编码规范
- 除了这三节讲到的比较细节的知识点之外，最后，还有一条非常重要的，那就是，项目、团队，甚至公司，一定要制定统一的编码规范，并且通过 Code Review 督促执行，这对提高代码质量有立竿见影的效果。

## 如何发现代码质量问题
1. 常规checklist
- 目录设置是否合理、模块划分是否清晰、代码结构是否满足“高内聚、松耦合”？
- 是否遵循经典的设计原则和设计思想（SOLID、DRY、KISS、YAGNI、LOD 等）？
- 设计模式是否应用得当？是否有过度设计？
- 代码是否容易扩展？如果要添加新功能，是否容易实现？
- 代码是否可以复用？是否可以复用已有的项目代码或类库？是否有重复造轮子？
- 代码是否容易测试？单元测试是否全面覆盖了各种正常和异常的情况？
- 代码是否易读？是否符合编码规范（比如命名和注释是否恰当、代码风格是否一致等）？

2. 业务checklist
- 代码是否实现了预期的业务需求？
- 逻辑是否正确？是否处理了各种异常情况？
- 日志打印是否得当？是否方便 debug 排查问题？
- 接口是否易用？是否支持幂等、事务等？
- 代码是否存在并发问题？是否线程安全？
- 性能是否有优化空间，比如，SQL、算法是否可以优化？
- 是否有安全漏洞？比如输入输出校验是否全面？