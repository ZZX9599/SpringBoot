# SpringBoot重点

# 1:详解依赖

SpringBoot开发团队认为原始的Spring程序`初始搭建的时候`可能有些繁琐，这个过程是可以简化的，那原始的Spring程序初始搭建过程都包含哪些东西了呢？为什么觉得繁琐呢？最基本的Spring程序至少有一个配置文件或配置类，用来描述Spring的配置信息，莫非这个文件都可以不写？此外现在企业级开发使用Spring大部分情况下是做web开发，如果做web开发的话，还要在加载web环境时加载时加载指定的spring配置，这都是最基本的需求了，不写的话怎么知道加载哪个配置文件/配置类呢？那换了SpringBoot技术以后呢，这些还要写吗？谜底稍后揭晓，先卖个关子

SpringBoot开发团队认为原始的Spring`程序开发的过程`也有些繁琐，这个过程仍然可以简化。开发过程无外乎使用什么技术，导入对应的jar包（或坐标）然后将这个技术的核心对象交给Spring容器管理，也就是配置成Spring容器管控的bean就可以了。这都是基本操作啊，难道这些东西SpringBoot也能帮我们简化

## 1.1:parent

SpringBoot关注到开发者在进行开发时，往往对依赖版本的选择具有固定的搭配格式，并且这些依赖版本的选择还不能乱搭配。比如A技术的2.0版，在与B技术进行配合使用时，与B技术的3.5版可以合作在一起工作，但是和B技术的3.7版合作开发使用时就有冲突。其实很多开发者都一直想做一件事情，就是将各种各样的技术配合使用的常见依赖版本进行收集整理，制作出了最合理的依赖版本配置方案，这样使用起来就方便多了

SpringBoot一看这种情况so easy啊，于是将所有的技术版本的常见使用方案都给开发者整理了出来，以后开发者使用时直接用它提供的版本方案，就不用担心冲突问题了，相当于SpringBoot做了无数个技术版本搭配的列表，这个技术搭配列表的名字叫做<font color="#ff0000"><b>parent</b></font>

<font color="#ff0000"><b>parent</b></font>自身具有很多个版本，每个<font color="#ff0000"><b>parent</b></font>版本中包含有几百个其他技术的版本号，不同的parent间使用的各种技术的版本号有可能会发生变化。当开发者使用某些技术时，直接使用SpringBoot提供的<font color="#ff0000"><b>parent</b></font>就行了，由<font color="#ff0000"><b>parent</b></font>帮助开发者统一的进行各种技术的版本管理

比如你现在要使用Spring配合MyBatis开发，没有parent之前怎么做呢？选个Spring的版本，再选个MyBatis的版本，再把这些技术使用时关联的其他技术的版本逐一确定下来。当你Spring的版本发生变化需要切换时，你的MyBatis版本有可能也要跟着切换，关联技术呢？可能都要切换，而且切换后还可能出现其他问题。现在这一切工作都可以交给parent来做了。你无需关注这些技术间的版本冲突问题，你只需要关注你用什么技术就行了，冲突问题由<font color="#ff0000"><b>parent</b></font>负责处理

有人可能会提出来，万一<font color="#ff0000"><b>parent</b></font>给我导入了一些我不想使用的依赖怎么办？记清楚，这一点很关键，<font color="#ff0000"><b>parent</b></font>仅仅帮我们进行版本管理，它不负责帮你导入坐标，说白了用什么还是你自己定，只不过版本不需要你管理了。整体上来说，<font color="#ff0000"><b>使用parent可以帮助开发者进行版本的统一管理。</b></font

<font color="#ff0000"><b>关注</b></font>：parent定义出来以后，并不是直接使用的，仅仅给了开发者一个说明书，但是并没有实际使用，这个一定要确认清楚



那SpringBoot又是如何做到这一点的呢？可以查阅SpringBoot的配置源码，看到这些定义。

- 项目中的pom.xml中继承了一个坐标

```XML
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.5.4</version>
</parent>
```

- 打开后可以查阅到其中又继承了一个坐标

```XML
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-dependencies</artifactId>
    <version>2.5.4</version>
</parent>
```

- 这个坐标中定义了两组信息

第一组是各式各样的依赖版本号属性，下面列出依赖版本属性的局部，可以看的出来，定义了若干个技术的依赖版本号。

```XML
<properties>
    <activemq.version>5.16.3</activemq.version>
    <aspectj.version>1.9.7</aspectj.version>
    <assertj.version>3.19.0</assertj.version>
    <commons-codec.version>1.15</commons-codec.version>
    <commons-dbcp2.version>2.8.0</commons-dbcp2.version>
    <commons-lang3.version>3.12.0</commons-lang3.version>
    <commons-pool.version>1.6</commons-pool.version>
    <commons-pool2.version>2.9.0</commons-pool2.version>
    <h2.version>1.4.200</h2.version>
    <hibernate.version>5.4.32.Final</hibernate.version>
    <hibernate-validator.version>6.2.0.Final</hibernate-validator.version>
    <httpclient.version>4.5.13</httpclient.version>
    <jackson-bom.version>2.12.4</jackson-bom.version>
    <javax-jms.version>2.0.1</javax-jms.version>
    <javax-json.version>1.1.4</javax-json.version>
    <javax-websocket.version>1.1</javax-websocket.version>
    <jetty-el.version>9.0.48</jetty-el.version>
    <junit.version>4.13.2</junit.version>
</properties>
```

第二组是各式各样的依赖坐标信息，可以看出依赖坐标定义中没有具体的依赖版本号，而是引用了第一组信息中定义的依赖版本属性值.

```XML
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-core</artifactId>
            <version>${hibernate.version}</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>${junit.version}</version>
        </dependency>
    </dependencies>
</dependencyManagement>
```

<font color="#ff0000"><b>关注</b></font>：上面的依赖坐标定义是出现在<dependencyManagement>标签中的，是对引用坐标的依赖管理，并不是实际使用的坐标。因此当你的项目中继承了这组parent信息后，在不使用对应坐标的情况下，前面的这组定义是不会具体导入某个依赖的。

<font color="#ff0000"><b>关注</b></font>：因为在maven中继承机会只有一次，上述继承的格式还可以切换成导入的形式进行，并且在阿里云的starter创建工程时就使用了此种形式。

```XML
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>${spring-boot.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

**总结**

1. 开发SpringBoot程序要继承spring-boot-starter-parent
2. spring-boot-starter-parent中定义了若干个依赖管理
3. 继承parent模块可以避免多个依赖使用相同技术时出现依赖版本冲突
4. 继承parent的形式也可以采用引入依赖的形式实现效果

**思考**

parent中定义了若干个依赖版本管理，但是我们继承了它，并没有使用这些依赖，那这个依赖的设定也加入不到我们的项目里面，设定也就不生效啊，究竟谁在使用这些定义呢？



## 1.2:starter

SpringBoot关注到实际开发时，开发者对于依赖坐标的使用往往都有一些固定的组合方式，比如使用spring-webmvc就一定要使用spring-web。每次都要固定搭配着写，非常繁琐，而且格式固定，没有任何技术含量。

SpringBoot一看这种情况，看来需要给开发者带来一些帮助了。安排，把所有的技术使用的固定搭配格式都给开发出来，以后你用某个技术，就不用每次写一堆依赖了，还容易写错，我给你做一个东西，代表一堆东西，开发者使用的时候，直接用我做好的这个东西就好了，对于这样的固定技术搭配，SpringBoot给它起了个名字叫做<font color="#ff0000"><b>starter</b></font>

starter定义了使用某种技术时对于依赖的固定搭配格式，也是一种最佳解决方案，<font color="#ff0000"><b>使用starter可以帮助开发者减少依赖配置</b></font>。

这个东西其实在入门案例里面已经使用过了，入门案例中的web功能就是使用这种方式添加依赖的。可以查阅SpringBoot的配置源码，看到这些定义。

- 项目中的pom.xml定义了使用SpringMVC技术，但是并没有写SpringMVC的坐标，而是添加了一个名字中包含starter的依赖

```XML
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

- 在spring-boot-starter-web中又定义了若干个具体依赖的坐标

```XML
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
        <version>2.5.4</version>
        <scope>compile</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-json</artifactId>
        <version>2.5.4</version>
        <scope>compile</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-tomcat</artifactId>
        <version>2.5.4</version>
        <scope>compile</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-web</artifactId>
        <version>5.3.9</version>
        <scope>compile</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.3.9</version>
        <scope>compile</scope>
    </dependency>
</dependencies>
```

之前提到过开发SpringMVC程序需要导入spring-webmvc的坐标和spring整合web开发的坐标，就是上面这组坐标中的最后两个了。

但是我们发现除了这两个坐标，还有其他的坐标。比如第二个，叫做spring-boot-starter-json。看名称就知道，这个是与json有关的坐标了，但是看名字发现和最后两个又不太一样，它的名字中也有starter，打开看看里面有什么？

```XML
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
        <version>2.5.4</version>
        <scope>compile</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-web</artifactId>
        <version>5.3.9</version>
        <scope>compile</scope>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.12.4</version>
        <scope>compile</scope>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.datatype</groupId>
        <artifactId>jackson-datatype-jdk8</artifactId>
        <version>2.12.4</version>
        <scope>compile</scope>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.datatype</groupId>
        <artifactId>jackson-datatype-jsr310</artifactId>
        <version>2.12.4</version>
        <scope>compile</scope>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.module</groupId>
        <artifactId>jackson-module-parameter-names</artifactId>
        <version>2.12.4</version>
        <scope>compile</scope>
    </dependency>
</dependencies>
```

我们可以发现，这个starter中又包含了若干个坐标，其实就是使用SpringMVC开发通常都会使用到Json，使用json又离不开这里面定义的这些坐标，看来还真是方便，SpringBoot把我们开发中使用的东西能用到的都给提前做好了。你仔细看完会发现，里面有一些你没用过的。的确会出现这种过量导入的可能性，没关系，可以通过maven中的排除依赖剔除掉一部分。不过你不管它也没事，大不了就是过量导入呗。

到这里基本上得到了一个信息，使用starter可以帮开发者快速配置依赖关系。以前写依赖3个坐标的，现在写导入一个就搞定了，就是加速依赖配置的。

**starter与parent的区别**

朦朦胧胧中感觉starter与parent好像都是帮助我们简化配置的，但是功能又不一样，梳理一下。

<font color="#ff0000"><b>starter</b></font>是一个坐标中定了若干个坐标，以前写多个的，现在写一个，<font color="#ff0000"><b>是用来减少依赖配置的书写量的</b></font>。

<font color="#ff0000"><b>parent</b></font>是定义了几百个依赖版本号，以前写依赖需要自己手工控制版本，现在由SpringBoot统一管理，这样就不存在版本冲突了，<font color="#ff0000"><b>是用来减少依赖冲突的</b></font>。

总结就是：

- parent是统一定义版本号的，防止各类技术出现冲突
- starter是一个带有starter的依赖，用来简化某一类开发功能的整合依赖，不用我们一个个导入



**实际开发应用方式**

- 实际开发中如果需要用什么技术，先去找有没有这个技术对应的starter

  - 如果有对应的starter，直接写starter，而且无需指定版本，版本由parent提供
  - 如果没有对应的starter，手写坐标即可

- 实际开发中如果发现坐标出现了冲突现象，确认你要使用的可行的版本号，使用手工书写的方式添加对应依赖，覆盖SpringBoot提供给我们的配置管理

  - 方式一：直接写坐标
  - 方式二：覆盖<properties>中定义的版本号，就是下面这堆东西了，哪个冲突了覆盖哪个就OK了
    - 我们自己在<properties>里写了版本号，就会覆盖父类的版本号

  ```XML
  <properties>
      <activemq.version>5.16.3</activemq.version>
      <aspectj.version>1.9.7</aspectj.version>
      <assertj.version>3.19.0</assertj.version>
      <commons-codec.version>1.15</commons-codec.version>
      <commons-dbcp2.version>2.8.0</commons-dbcp2.version>
      <commons-lang3.version>3.12.0</commons-lang3.version>
      <commons-pool.version>1.6</commons-pool.version>
      <commons-pool2.version>2.9.0</commons-pool2.version>
      <h2.version>1.4.200</h2.version>
      <hibernate.version>5.4.32.Final</hibernate.version>
      <hibernate-validator.version>6.2.0.Final</hibernate-validator.version>
      <httpclient.version>4.5.13</httpclient.version>
      <jackson-bom.version>2.12.4</jackson-bom.version>
      <javax-jms.version>2.0.1</javax-jms.version>
      <javax-json.version>1.1.4</javax-json.version>
      <javax-websocket.version>1.1</javax-websocket.version>
      <jetty-el.version>9.0.48</jetty-el.version>
      <junit.version>4.13.2</junit.version>
  </properties>
  ```

<font color="#f0f"><b>温馨提示</b></font>

SpringBoot官方给出了好多个starter的定义，方便我们使用，而且名称都是如下格式

```
命名规则：spring-boot-starter-技术名称
```

那非官方定义的也有吗？有的，具体命名方式到整合技术的章节再说



所有的starter中都会依赖下面这个starter，叫做spring-boot-starter。这个starter是所有的SpringBoot的starter的基础依赖，里面定义了SpringBoot相关的基础配置

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
    <version>2.5.4</version>
    <scope>compile</scope>
</dependency>
```

## 1.3:引导类

配置说完了，我们发现SpringBoot确实帮助我们减少了很多配置工作，下面说一下程序是如何运行的。目前程序运行的入口就是SpringBoot工程创建时自带的那个类，也就是带有main方法的那个类，运行这个类就可以启动SpringBoot工程的运行。

```java
@SpringBootApplication
public class SpringbootQuickstartApplication {
    public static void main(String[] args) {
        SpringApplication.run(Springboot0101QuickstartApplication.class, args);
    }
}
```

SpringBoot本身是为了加速Spring程序的开发的，而Spring程序运行的基础是需要创建Spring容器对象（IoC容器）并将所有的对象放置到Spring容器中管理，也就是一个一个的Bean。现在改用SpringBoot加速开发Spring程序，这个容器还在吗？这个疑问不用说，一定在。其实当前这个类运行后就会产生一个Spring容器对象，并且可以将这个对象保存起来，通过容器对象直接操作Bean。

```JAVA
@SpringBootApplication
public class QuickstartApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(QuickstartApplication.class, args);
        BookController bean = ctx.getBean(BookController.class);
        System.out.println("bean======>" + bean);
    }
}
```

通过上述操作不难看出，其实SpringBoot程序启动还是创建了一个Spring容器对象。当前运行的这个类在

SpringBoot程序中是所有功能的入口，称为<font color="#ff0000"><b>引导类</b></font>。

作为一个引导类最典型的特征就是当前类上方声明了一个注解<font color="#ff0000"><b>@SpringBootApplication</b></font>。

程序现在已经运行了，通过引导类的main方法运行了起来。但是运行java程序不应该是执行完就结束了吗？

但是我们现在明显是启动了一个web服务器啊，不然网页怎么能正常访问呢？这个服务器是在哪里写的呢？



## 1.4:内嵌Tomcat

当勾选了Spring-web的功能，并且导入了对应的starter。

```XML
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

SpringBoot发现，既然你要做web程序，肯定离不开使用web服务器，这样吧，帮人帮到底，送佛送到西，我帮你搞一个web服务器，你要愿意用的，直接使用就好了。SpringBoot又琢磨，提供一种服务器万一不满足开发者需要呢？干脆我再多给你几种选择，你随便切换。万一你不想用我给你提供的，也行，你可以自己搞。

由于这个功能不属于程序的主体功能，可用可不用，于是乎SpringBoot将其定位成辅助功能，别小看这么一个辅助功能，它可是帮我们开发者又减少了好多的设置性工作。

下面就围绕着这个内置的web服务器，也可以说是内置的tomcat服务器来研究几个问题：

1. 这个服务器在什么位置定义的
2. 这个服务器是怎么运行的
3. 这个服务器如果想换怎么换？虽然这个需求很垃圾，搞得开发者会好多web服务器一样，用别人提供好的不香么？非要自己折腾



**内嵌Tomcat定义位置**

说到定义的位置，我们就想，如果我们不开发web程序，用的着web服务器吗？肯定用不着啊。那如果这个东西被加入到你的程序中，伴随着什么技术进来的呢？肯定是web相关的功能啊，没错，就是前面导入的web相关的starter做的这件事。

```XML
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

打开web对应的starter查看导入了哪些东西。

```XML
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
        <version>2.5.4</version>
        <scope>compile</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-json</artifactId>
        <version>2.5.4</version>
        <scope>compile</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-tomcat</artifactId>
        <version>2.5.4</version>
        <scope>compile</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-web</artifactId>
        <version>5.3.9</version>
        <scope>compile</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.3.9</version>
        <scope>compile</scope>
    </dependency>
</dependencies>
```

第三个依赖就是tomcat对应的东西了，居然也是一个starter，再打开看看。

```XML
<dependencies>
    <dependency>
        <groupId>jakarta.annotation</groupId>
        <artifactId>jakarta.annotation-api</artifactId>
        <version>1.3.5</version>
        <scope>compile</scope>
    </dependency>
    <dependency>
        <groupId>org.apache.tomcat.embed</groupId>
        <artifactId>tomcat-embed-core</artifactId>
        <version>9.0.52</version>
        <scope>compile</scope>
        <exclusions>
            <exclusion>
                <artifactId>tomcat-annotations-api</artifactId>
                <groupId>org.apache.tomcat</groupId>
            </exclusion>
        </exclusions>
    </dependency>
    <dependency>
        <groupId>org.apache.tomcat.embed</groupId>
        <artifactId>tomcat-embed-el</artifactId>
        <version>9.0.52</version>
        <scope>compile</scope>
    </dependency>
    <dependency>
        <groupId>org.apache.tomcat.embed</groupId>
        <artifactId>tomcat-embed-websocket</artifactId>
        <version>9.0.52</version>
        <scope>compile</scope>
        <exclusions>
            <exclusion>
                <artifactId>tomcat-annotations-api</artifactId>
                <groupId>org.apache.tomcat</groupId>
            </exclusion>
        </exclusions>
    </dependency>
</dependencies>
```

这里面有一个核心的坐标，tomcat-embed-core，叫做tomcat内嵌核心。就是这个东西把tomcat功能引入到了我们的程序中的。目前解决了第一个问题，找到根儿了，谁把tomcat引入到程序中的？spring-boot-starter-web中的spring-boot-starter-tomcat做的。之所以你感觉很奇妙的原因就是，这个东西是默认加入到程序中了，所以感觉很神奇，居然什么都不做，就有了web服务器对应的功能。再来说第二个问题，这个服务器是怎么运行的。

**内嵌Tomcat运行原理**

Tomcat服务器是一款软件，而且是一款使用java语言开发的软件，熟悉tomcat的话应该知道tomcat安装目录中保存有很多jar文件。

下面的问题来了，既然是使用java语言开发的，运行的时候肯定符合java程序运行的原理，java程序运行靠的是什么？对象呀，一切皆对象，万物皆对象。那tomcat运行起来呢？也是对象啊。

如果是对象，那Spring容器是用来管理对象的，这个对象能交给Spring容器管理吗？把吗去掉，是个对象都可以交给Spring容器管理，行了，这下通了，tomcat服务器运行其实是以对象的形式在Spring容器中运行的。怪不得我们没有安装这个tomcat但是还能用，闹了白天这东西最后是以一个对象的形式存在，保存在Spring容器中悄悄运行的。具体运行的是什么呢？其实就是上前面提到的那个tomcat内嵌核心。

```XML
<dependencies>
    <dependency>
        <groupId>org.apache.tomcat.embed</groupId>
        <artifactId>tomcat-embed-core</artifactId>
        <version>9.0.52</version>
        <scope>compile</scope>
    </dependency>
</dependencies>
```

那既然是个对象，如果把这个对象从Spring容器中去掉是不是就没有web服务器的功能呢？是这样的，通过依赖排除可以去掉这个web服务器功能。

```XML
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <exclusions>
            <exclusion>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-tomcat</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
</dependencies>
```

上面对web-starter做了一个操作，使用maven的排除依赖去掉了使用tomcat的starter。这下好了，容器中肯定没有这个对象了，重新启动程序可以观察到程序运行了，但是并没有像之前那样运行后是一个一直运行的服务，而是直接停掉了，就是这个原因。

**更换内嵌Tomcat**

那根据上面的操作我们思考是否可以换个服务器呢？必须的嘛。根据SpringBoot的工作机制，用什么技术就加入什么依赖就行了。SpringBoot提供了3款内置的服务器：

- tomcat(默认)：apache出品，粉丝多，应用面广，负载了若干较重的组件

- jetty：更轻量级，负载性能远不及tomcat

- undertow：负载性能勉强跑赢tomcat

  想用哪个，加个坐标就OK。前提是把tomcat排除掉，因为tomcat是默认加载的。

```XML
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <exclusions>
            <exclusion>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-tomcat</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jetty</artifactId>
    </dependency>
</dependencies>
```

现在就已经成功替换了web服务器，核心思想就是用什么加入对应坐标就可以了。如果有starter，优先使用starter。

**总结**

1. 内嵌Tomcat服务器是SpringBoot辅助功能之一
2. 内嵌Tomcat工作原理是将Tomcat服务器作为对象运行，并将该对象交给Spring容器管理
3. 变更内嵌服务器思想是去除现有服务器，添加全新的服务器



# 2:配置文件

## 2.1:配置文件优先级

```
application.properties  >  application.yml  >  application.yaml
```



## 2.2:数据读取

对于yaml文件中的数据，其实你就可以想象成这就是一个小型的数据库，里面保存有若干数据，每个数据都有一个独立的名字，如果你想读取里面的数据，肯定是支持的，下面就介绍3种读取数据的方式



### 1:读取单一数据

配置文件保存的单个数据，可以使用Spring中的注解@Value读取单个数据

属性名引用方式：<font color="#ff0000"><b>${一级属性名.二级属性名……}</b></font>

<img src=" https://zzx-note.oss-cn-beijing.aliyuncs.com/springboot/image-20211126180433356-16648911978021.png" alt="image-20211126180433356" style="zoom:80%;" />

记得使用@Value注解时，要将该注解`写在某一个指定的Spring管控的bean的属性名上方`，这样当bean进行初始化时候就可以读取到对应的单一数据了



**总结**

1. 使用@Value配合SpEL读取单个数据
2. 如果数据存在多层级，依次书写层级名称即可

### 2:读取全部数据

读取单一数据可以解决读取数据的问题，但是如果定义的数据量过大，这么一个一个书写肯定会累死人的，SpringBoot提供了一个对象，能够把所有的数据都封装到这一个对象中，这个对象叫做Environment，使用自动装配注解可以将所有的yaml数据封装到这个对象中

<img src=" https://zzx-note.oss-cn-beijing.aliyuncs.com/springboot/image-20211126180738569.png" alt="image-20211126180738569" style="zoom:80%;" />

数据封装到了Environment对象中，获取属性时，通过Environment的接口操作进行，具体方法是getProperties（String），参数填写属性名即可

**总结**

1. 使用Environment对象封装全部配置信息
2. 使用@Autowired自动装配数据到Environment对象中



### 3:读取对象数据

单一数据读取书写比较繁琐，全数据读取封装的太厉害了，每次拿数据还要一个一个的getProperties（）

总之用起来都不是很舒服。Java是一个面向对象的语言，很多情况下我们会将一组数据封装成一个对象

SpringBoot也提供了可以将一组yaml对象数据封装一个Java对象的操作

首先定义一个对象，并`将该对象纳入Spring管控的范围`，也就是定义成一个bean，然后使用注解

@ConfigurationProperties指定该对象加载哪一组yaml中配置的信息。

<img src=" https://zzx-note.oss-cn-beijing.aliyuncs.com/springboot/image-20211126181126382.png" alt="image-20211126181126382" style="zoom:80%;" />

这个@ConfigurationProperties必须告诉他加载的数据前缀是什么，这样指定前缀下的所有属性就封装到这个

对象中。记得数据属性名要与对象的变量名一一对应，不然没法封装。其实以后如果你要定义一组数据自

己使用，就可以先写一个对象，然后定义好属性，下面到配置中根据这个格式书写即可

​	<img src=" https://zzx-note.oss-cn-beijing.aliyuncs.com/springboot/image-20211126181423432.png" alt="image-20211126181423432" style="zoom:80%;" />

<font color="#f0f"><b>温馨提示</b></font>

细心的小伙伴会发现一个问题，自定义的这种数据在yaml文件中书写时没有弹出提示，咱们到原理篇再揭秘如何弹出提示

**总结**

1. 使用@ConfigurationProperties注解绑定配置信息到封装类中
2. 封装类需要定义为Spring管理的bean，否则无法进行属性注入



## 2.3:配置文件数据引用

如果你在书写yaml数据时，经常出现如下现象，比如很多个文件都具有相同的目录前缀

```YAML
center:
	dataDir: /usr/local/fire/data
    tmpDir: /usr/local/fire/tmp
    logDir: /usr/local/fire/log
    msgDir: /usr/local/fire/msgDir
```

或者

```YAML
center:
	dataDir: D:/usr/local/fire/data
    tmpDir: D:/usr/local/fire/tmp
    logDir: D:/usr/local/fire/log
    msgDir: D:/usr/local/fire/msgDir
```

这个时候你可以使用引用格式来定义数据，其实就是搞了个变量名，然后引用变量了，格式如下：

```YAML
baseDir: /usr/local/fire
center:
    dataDir: ${baseDir}/data
    tmpDir: ${baseDir}/tmp
    logDir: ${baseDir}/log
    msgDir: ${baseDir}/msgDir
```

还有一个注意事项，在书写字符串时，如果需要使用转义字符，需要将数据字符串使用双引号包裹起来

```YAML
lesson: "Spring\tboot\nlesson"
```

**总结**

1. 在配置文件中可以使用`${属性名}`方式引用属性值
2. 如果属性中出现特殊字符，可以使用双引号包裹起来作为字符解析

​		到这里有关yaml文件的基础使用就先告一段落，实用篇中再继续研究更深入的内容。



## 2.4:临时属性

SpringBoot提供了灵活的配置方式，如果你发现你的项目中有个别属性需要重新配置，可以使用临时属性的方式快速修改某些配置。方法也特别简单，在启动的时候添加上对应参数就可以了

```JAVA
java –jar springboot.jar –-server.port=80
```

上面的命令是启动SpringBoot程序包的命令，在命令输入完毕后，空一格，然后输入`两个 - 号`，下面按照

属性名=属性值的形式添加对应参数就可以了。记得，这里的格式不是yaml中的书写格式，`当属性存在多级`

`名称时，中间使用 . 分隔`，和properties文件中的属性格式完全相同

如果你发现要修改的属性不止一个，可以按照上述格式继续写，`属性与属性之间使用空格分隔`

```JAVA
java –jar springboot.jar –-server.port=80 --logging.level.root=debug
```



## 2.5:属性加载优先级

现在我们的程序配置受两个地方控制了，第一配置文件，第二临时属性。并且我们发现临时属性的加载优先级要高于配置文件的。那是否还有其他的配置方式呢？其实是有的，而且还不少，打开官方文档中对应的内容，就可以查看配置读取的优先顺序

地址：https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-external-config



你现在加载了一个user.name属性。结果你发现出来的结果和你想的不一样，那肯定是别的优先级比你高的属性覆盖你的配置属性了，那你就可以看着这个顺序挨个排查。哪个位置有可能覆盖了你的属性



## 2.6:开发环境使用临时属性

![image-20221004225657056]( https://zzx-note.oss-cn-beijing.aliyuncs.com/springboot/image-20221004225657056.png)

做到这里其实可以产生一个思考了，如果对java编程熟悉的小伙伴应该知道，我们运行main方法的时候，如果想使用main方法的参数，也就是下面的args参数，就是在上面这个位置添加的参数。

```JAVA
public static void main(String[] args) {
}
```

原来是这样，通过这个args就可以获取到参数。再来看我们的引导类是如何书写的

```JAVA
public static void main(String[] args) {
    SpringApplication.run(SSMPApplication.class,args);
}
```

这个args参数居然传递给了run方法，看来在Idea中配置的临时参数就是通过这个位置传递到我们的程序中的。言外之意，这里如果不用这个args是不是就断开了外部传递临时属性的入口呢？是这样的，我们可以使用下面的调用方式，这样外部临时属性就无法进入到SpringBoot程序中了。

```JAVA
public static void main(String[] args) {
    SpringApplication.run(SSMPApplication.class);
}
```

或者还可以使用如下格式来玩这个操作，就是将配置不写在配置文件中，直接写成一个字符串数组，传递给程序入口。当然，这种做法并没有什么实际开发意义

```JAVA
public static void main(String[] args) {
    String[] arg = new String[1];
    arg[0] = "--server.port=8082";
    SpringApplication.run(SSMPApplication.class, arg);
}
```

**总结**

启动SpringBoot程序时，可以选择是否使用命令行属性为SpringBoot程序传递启动属性

临时属性好用是好用，就是写的多了会很麻烦。比如我现在有个需求，上线的时候使用临时属性配置20个

值，这下可麻烦了，能不能搞得简单点，集中管理一下



## 2.7:配置文件优先级

SpringBoot提供了配置文件和临时属性的方式来对程序进行配置。前面一直说的是临时属性，这一节要说说配置文件了。其实这个配置文件我们一直在使用，只不过我们用的是SpringBoot提供的4级配置文件中的其中一个级别。4个级别分别是：

- 类路径下配置文件（一直使用的是这个，也就是resources目录中的application.yml文件）
- 类路径下config目录下配置文件
- 程序包所在目录中配置文件
- 程序包所在目录中config目录下配置文件

其实上述4种文件是提供给你了4种配置文件书写的位置，功能都是一样的，都是做配置的。那大家关心的就

是差别了，没错，就是因为位置不同，产生了差异。总体上来说，4种配置文件如果都存在的话，有一个优

先级的问题

上面4个文件的加载优先顺序为

1. file ：config/application.yml **【最高】**
2. file ：application.yml
3. classpath：config/application.yml
4. classpath：application.yml  **【最低】**



1. 配置文件分为4种

   - 项目类路径配置文件：服务于开发人员本机开发与测试
   - 项目类路径config目录中配置文件：服务于项目经理整体调控
   - 工程路径配置文件：服务于运维人员配置涉密线上环境
   - 工程路径config目录中配置文件：服务于运维经理整体调控
2. 多层级配置文件间的属性采用叠加并覆盖的形式作用于程序



## 2.8:自定义配置文件名称

**方式一：使用临时属性设置配置文件名，注意仅仅是名称，不要带扩展名**

![image-20221004231410765]( https://zzx-note.oss-cn-beijing.aliyuncs.com/springboot/image-20221004231410765.png)



**方式二：使用临时属性设置配置文件路径，这个是全路径名**

![image-20221004231438866]( https://zzx-note.oss-cn-beijing.aliyuncs.com/springboot/image-20221004231438866.png)

使用的属性一个是spring.config.name，另一个是spring.config.location，这个一定要区别清楚

# 3:整合其他技术

## 3.1:整合JUnit

SpringBoot技术的定位用于简化开发，再具体点是简化Spring程序的开发。所以在整合任意技术的时候，如果你想直观感触到简化的效果，你必须先知道使用非SpringBoot技术时对应的整合是如何做的，然后再看基于SpringBoot的整合是如何做的，才能比对出来简化在了哪里。

我们先来看一下不使用SpringBoot技术时，Spring整合JUnit的制作方式

```JAVA
//加载spring整合junit专用的类运行器
@RunWith(SpringJUnit4ClassRunner.class)
//指定对应的配置信息
@ContextConfiguration(classes = SpringConfig.class)
public class AccountServiceTestCase {
    //注入你要测试的对象
    @Autowired
    private AccountService accountService;
    @Test
    public void testGetById(){
        //执行要测试的对象对应的方法
        System.out.println(accountService.findById(2));
    }
}

```

其中核心代码是前两个注解，第一个注解@RunWith是设置Spring专用的测试类运行器，简单说就是Spring程序执行程序有自己的一套独立的运行程序的方式，不能使用JUnit提供的类运行方式了，必须指定一下，但是格式是固定的，琢磨一下，<font color="#ff0000"><b>每次都指定一样的东西，这个东西写起来没有技术含量啊</b></font>，第二个注解@ContextConfiguration是用来设置Spring核心配置文件或配置类的，简单说就是加载Spring的环境你要告诉Spring具体的环境配置是在哪里写的，虽然每次加载的文件都有可能不同，但是仔细想想，如果文件名是固定的，这个貌似也是一个固定格式。既然<font color="#ff0000"><b>有可能是固定格式，那就有可能每次都写一样的东西，也是一个没有技术含量的内容书写</b></font>

SpringBoot就抓住上述两条没有技术含量的内容书写进行开发简化，能走默认值的走默认值，能不写的就不写，具体格式如下

```JAVA
@SpringBootTest
class Springboot04JunitApplicationTests {
    //注入你要测试的对象
    @Autowired
    private BookDao bookDao;
    @Test
    void contextLoads() {
        //执行要测试的对象对应的方法
        bookDao.save();
        System.out.println("two...");
    }
}
```

看看这次简化成什么样了，一个注解就搞定了，而且还没有参数，再体会SpringBoot整合其他技术的优势在哪里，就两个字——<font color="#ff0000"><b>简化</b></font>。使用一个注解@SpringBootTest替换了前面两个注解。至于内部是怎么回事？和之前一样，只不过都走默认值。

这个时候有人就问了，`你加载的配置类或者配置文件是哪一个？`就是我们前面启动程序使用的引导类。如果想手工指定引导类有两种方式



### 1:指定配置类方式一

第一种方式使用属性的形式进行，在注解@SpringBootTest中添加classes属性指定配置类

```JAVA
@SpringBootTest(classes = Springboot04JunitApplication.class)
class Springboot04JunitApplicationTests {
    //注入你要测试的对象
    @Autowired
    private BookDao bookDao;
    @Test
    void contextLoads() {
        //执行要测试的对象对应的方法
        bookDao.save();
        System.out.println("two...");
    }
}
```

### 2:指定配置类方式二

第二种方式回归原始配置方式，仍然使用@ContextConfiguration注解进行，效果是一样的

```JAVA
@SpringBootTest
@ContextConfiguration(classes = Springboot04JunitApplication.class)
class Springboot04JunitApplicationTests {
    //注入你要测试的对象
    @Autowired
    private BookDao bookDao;
    @Test
    void contextLoads() {
        //执行要测试的对象对应的方法
        bookDao.save();
        System.out.println("two...");
    }
}
```

<font color="#f0f"><b>温馨提示</b></font>

使用SpringBoot整合JUnit需要保障导入test对应的starter，由于初始化项目时此项是默认导入的，所以此处没有提及，其实和之前学习的内容一样，用什么技术导入对应的starter即可。

**总结**

1. 导入测试对应的starter
2. 测试类使用@SpringBootTest修饰
3. 使用自动装配的形式添加要测试的对象
4. 测试类如果存在于引导类所在包或子包中无需指定引导类
5. 测试类如果不存在于引导类所在的包或子包中需要通过classes属性指定引导类



## 3.2:整合MyBatis

### 1:回顾Spring整合

- 导入各类坐标
- Spring配置类【主配置类】
- MyBatis要交给Spring接管的bean【mybatis配置类】
- Jdbc配置类【数据源对应的bean，此处使用Druid数据源】
- 数据库连接信息（properties格式）

上述格式基本上是最简格式了，要写的东西还真不少。下面看看SpringBoot整合MyBaits格式



### 2:SpringBoot整合

```xml
<dependencies>
    <!--1.导入对应的starter-->
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>2.2.0</version>
    </dependency>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/ssm_db
    username: root
    password: root
```

本质就是：`mybatis-spring-boot-starter`把Mybatis核心的配置这些都已经配置好，放到Spring容器里了

MySQL驱动升级到8以后要求强制配置时区，如果不设置可能会出现问题

解决方案很简单，驱动url上面添加上对应设置就行了

```properties
url:jdbc:mysql://localhost:3306/ssm_db?serverTimezone=UTC
```

这里设置的UTC是全球标准时间，你也可以理解为是英国时间，中国处在东八区，需要在这个基础上加上8

小时，这样才能和中国地区的时间对应的，也可以修改配置为Asia/Shanghai，同样可以解决这个问题

```properties
url:jdbc:mysql://localhost:3306/ssm_db?serverTimezone=Asia/Shanghai
```

如果不想每次都设置这个东西，也可以去修改mysql中的配置文件mysql.ini

在mysqld项中添加default-time-zone=+8:00也可以解决这个问题



## 3.3:整合MyBatisPlus

```xml
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.4.3</version>
</dependency>
```

关于这个坐标，此处要说明一点，之前我们看的starter都是`spring-boot-starter-???`

| starter所属 | 命名规则                                                    | 示例                                                      |
| ----------- | ----------------------------------------------------------- | --------------------------------------------------------- |
| 官方提供    | spring-boot-starter-技术名称                                | spring-boot-starter-web <br/>spring-boot-starter-test     |
| 第三方提供  | 第三方技术名称-spring-boot-starter                          | mybatis-spring-boot-starter<br/>druid-spring-boot-starter |
| 第三方提供  | 第三方技术名称-boot-starter（第三方技术名称过长，简化命名） | mybatis-plus-boot-starter                                 |



## 3.4:整合Druid

前面整合MyBatis和MyBatisPlus的时候，使用的数据源对象都是SpringBoot默认的数据源对象，下面我们手

工控制一下，自己指定了一个数据源对象，Druid。

在没有指定数据源时，我们的配置如下：

```YAML
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/ssm_db?serverTimezone=Asia/Shanghai
    username: root
    password: root
```

此时虽然没有指定数据源，但是根据SpringBoot的德行，肯定帮我们选了一个它认为最好的数据源对象

这就是HiKari。通过启动日志可以查看到对应的身影。

```apl
2021-11-29 09:39:15.202  INFO 12260 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2021-11-29 09:39:15.208  WARN 12260 --- [           main] com.zaxxer.hikari.util.DriverDataSource  : Registered driver with driverClassName=com.mysql.jdbc.Driver was not found, trying direct instantiation.
2021-11-29 09:39:15.551  INFO 12260 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
```



配置：

```xml
<dependencies>
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid</artifactId>
        <version>1.1.16</version>
    </dependency>
</dependencies>
```

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/ssm_db?serverTimezone=UTC
    username: root
    password: root
    type: com.alibaba.druid.pool.DruidDataSource
```

目前的数据源配置格式是一个通用格式，不管你换什么数据源都可以用这种形式进行配置。但是新的问题又来了，如果对数据源进行个性化的配置，例如配置数据源对应的连接数量，这个时候就有新的问题了。每个数据源技术对应的配置名称都一样吗？肯定不是啊，各个厂商不可能提前商量好都写一样的名字啊，怎么办？就要使用专用的配置格式了。这个时候上面这种通用格式就不能使用了，怎么办？还能怎么办？按照SpringBoot整合其他技术的通用规则来套啊，导入对应的starter，进行相应的配置即可

**步骤①**：导入对应的starter

```XML
<dependencies>
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid-spring-boot-starter</artifactId>
        <version>1.2.6</version>
    </dependency>
</dependencies>
```

**步骤②**：修改配置

```YAML
spring:
  datasource:
    druid:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://localhost:3306/ssm_db?serverTimezone=UTC
      username: root
      password: root
```

注意观察，配置项中，在datasource下面并不是直接配置url这些属性的，而是先配置了一个druid节点，然后再配置的url这些东西。言外之意，url这个属性是druid下面的属性，那你能想到什么？除了这4个常规配置外，还有druid专用的其他配置。通过提示功能可以打开druid相关的配置查阅

本质也是：SpringBoot根据配置和Starter帮我们创建对象放入容器里面







# 4:打包与运行

<font color="#f0f"><b>温馨提示</b></font>

企业项目上线为了保障环境适配性会采用下面流程发布项目，这里不讨论此过程。

1. 开发部门开发好了之后使用Git、SVN等版本控制工具上传工程到版本服务器
2. 服务器使用版本控制工具下载工程
3. 服务器上使用Maven工具在当前真机环境下重新构建项目
4. 启动服务



所谓打包指将程序转换成一个可执行的文件

所谓运行指不依赖开发环境执行打包产生的文件

上述两个操作都有对应的命令可以快速执行。



## 4.1:打jar包运行

SpringBoot程序是基于Maven创建的，在Maven中提供有打包的指令，叫做package

打包：本操作可以在Idea环境下执行

```
mvn package
```

运行：

```
java -jar 工程包名.jar
```

执行程序打包指令后，程序正常运行，与在Idea下执行程序没有区别。

<font color="#ff0000"><b>特别关注</b></font>：如果你的计算机中没有安装java的jdk环境，是无法正确执行上述操作的，因为程序执行使用的是java指令。

<font color="#ff0000"><b>特别关注</b></font>：在使用向导创建SpringBoot工程时，pom.xml文件中会有如下配置，这一段配置千万不能删除，否则打包后无法正常执行程序。

```XML
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

删除了我们再打包试试看

## 4.2:打包失败处理

打包好的运行，发现出现下面的情况：

<img src=" https://zzx-note.oss-cn-beijing.aliyuncs.com/springboot/image-20211201094223991.png" alt="image-20211201094223991"  />

要想搞清楚这个问题就要说说.jar文件的工作机制了，知道了这个东西就知道如何避免此类问题的发生了

搞java开发平时会接触很多jar包，比如mysql的驱动jar包，而上面我们打包自己的程序后得到的也是一个jar文件。但是这个是我们自己程序的jar包，这个时候如果你使用上面的java -jar指令去执行mysql的驱动jar包就会出现上述不可执行的现象，而我们的SpringBoot项目为什么能执行呢？其实是因为打包方式不一样，是不是springboot打包的会存在mysql这些jar包呢？



不删除上面的配置，打包之后查看jar包的BOOT-INF目录下，打开lib目录，里面显示了很多个jar文件，正是我们项目使用的那些jar包，全部被一起打包进来了



SpringBoot程序为了让自己打包生成的程序可以独立运行，不仅将项目中自己开发的内容进行了打包，还把当前工程运行需要使用的jar包全部打包进来了。为什么这样做呢？就是为了可以独立运行。不依赖程序包外部的任何资源可以独立运行当前程序



所以：spring-boot-maven-plugin插件用于将当前程序打包成一个可以独立运行的程序包



## 4.3:可能用得到

```
# 查询端口
netstat -ano

# 查询指定端口
netstat -ano |findstr "端口号"

# 根据进程PID查询进程名称
tasklist |findstr "进程PID号"

# 根据PID杀死任务
taskkill /F /PID "进程PID号"

# 根据进程名称杀死任务
taskkill -f -t -im "进程名称"
```



# 5:多环境开发

什么是多环境？其实就是说你的电脑上写的程序最终要放到别人的服务器上去运行。每个计算机环境不一样，这就是多环境。常见的多环境开发主要兼顾3种环境设置，开发环境——自己用的，测试环境——自己公司用的，生产环境——甲方爸爸用的。因为这是绝对不同的三台电脑，所以环境肯定有所不同，比如连接的数据库不一样，设置的访问端口不一样等等



## 5.1:yaml单一文件版

比如你自己开发时，配置你的端口如下：

```yaml
server:
  port: 80
```

​		如何想设计两组环境呢？中间使用`三个减号`分隔开

```yaml
server:
  port: 80
---
server:
  port: 81
```

​		如何区分两种环境呢？起名字呗

```yaml
spring:
	profiles: pro
server:
	port: 80
---
spring:
	profiles: dev
server:
	port: 81
```

那用哪一个呢？设置默认启动哪个就可以了

```yaml
spring:
	profiles: pro
server:
	port: 80
---
spring:
	profiles: dev
server:
	port: 81
---	
spring:
	config:
    	activate:
        	on-profile: pro
```

1. 多环境开发需要设置若干种常用环境，例如开发、生产、测试环境
2. yaml格式中设置多环境使用---区分环境设置边界
3. 每种环境的区别在于加载的配置属性不同
4. 启用某种环境时需要指定启动时使用该环境



## 5.2:yaml多文件版

将所有的配置都放在一个配置文件中，尤其是每一个配置应用场景都不一样，这显然不合理，于是就有了将一个配置文件拆分成多个配置文件的想法。拆分后，每个配置文件中写自己的配置，主配置文件中写清楚用哪一个配置文件就好了。

**主配置文件**

```yaml
spring:
	profiles:
		active: 
			on-profile: pro		# 启动pro
```

**application-pro.yaml**

```yaml
server:
	port: 80
```

**application-dev.yaml**

```yaml
server:
	port: 81
```

文件的命名规则为：application-环境名.yml

在配置文件中，如果某些配置项所有环境都一样，可以将这些项写入到主配置中

只有那些有区别的项才写入到环境配置文件中



## 5.3:多环境开发独立配置文件书写技巧

作为程序员在搞配置的时候往往处于一种分久必合合久必分的局面。开始先写一起，后来为了方便维护就拆

分。对于多环境开发也是如此，下面给大家说一下如何基于多环境开发做配置独立管理，务必掌握

将所有的配置根据功能对配置文件中的信息进行拆分，并制作成独立的配置文件，命名规则如下

- application-devDB.yml
- application-devRedis.yml
- application-devMVC.yml

使用include属性在激活指定环境的情况下，同时对多个环境进行加载使其生效，多个环境间使用逗号分隔

```yaml
spring:
	profiles:
    	active: dev
        include: devDB,devRedis,devMVC
```

现在相当于加载dev配置时，再加载对应的3组配置，从结构上就很清晰，用了什么，对应的名称是什么



但是上面的设置也有一个问题，比如我要切换dev环境为pro时，include也要修改。因为include属性只能使

用一次，这就比较麻烦了。SpringBoot从2.4版开始使用group属性替代include属性，降低了配置书写量。简

单说就是我先写好，你爱用哪个用哪个

```yaml
spring:
	profiles:
    	active: dev
        group:
        	"dev": devDB,devRedis,devMVC
      		"pro": proDB,proRedis,proMVC
      		"test": testDB,testRedis,testMVC
```

## 5.4:maven多环境

maven和SpringBoot同时设置多环境的话怎么搞。

要想处理这个冲突问题，你要先理清一个关系，究竟谁在多环境开发中其主导地位

也就是说如果现在都设置了多环境，谁的应该是保留下来的，另一个应该遵从相同的设置。

maven是做什么的？项目构建管理的，最终生成代码包的，SpringBoot是干什么的？简化开发的。简化，又

不是其主导作用。最终还是要靠maven来管理整个工程，所以SpringBoot应该听maven的

大体思想如下：

- 先在maven环境中设置用什么具体的环境
- 在SpringBoot中读取maven设置的环境即可

**maven中设置多环境（使用属性方式区分环境）**

```xml
<profiles>
    <profile>
        <id>env_dev</id>
        <properties>
            <profile.active>dev</profile.active>
        </properties>
        <activation>
            <activeByDefault>true</activeByDefault>		<!--默认启动环境-->
        </activation>
    </profile>
    <profile>
        <id>env_pro</id>
        <properties>
            <profile.active>pro</profile.active>
        </properties>
    </profile>
</profiles>
```

**SpringBoot中读取maven设置值**

```yaml
spring:
	profiles:
    	active: @profile.active@
```

上面的`@属性名@`就是读取maven中配置的属性值的语法格式。



# 6:日志

简单介绍一下。日志其实就是记录程序日常运行的信息，主要作用如下：

- 编程期调试代码
- 运营期记录信息
- 记录日常运营重要信息（峰值流量、平均响应时长……）
- 记录应用报错信息（错误堆栈）
- 记录运维过程数据（扩容、宕机、报警……）



## 6.1:使用日志工具

**步骤①**：添加日志记录操作

```JAVA
@RestController
@RequestMapping("/books")
public class BookController extends BaseClass{
    private static final Logger log = LoggerFactory.getLogger(BookController.class);
    @GetMapping
    public String getById(){
        log.debug("debug...");
        log.info("info...");
        log.warn("warn...");
        log.error("error...");
        return "springboot is running...2";
    }
}
```

上述代码中log对象就是用来记录日志的对象，下面的log.debug，log.info这些操作就是写日志的API了

使用的是SLF4J的日志门面，实现是logback的实现类

**步骤②**：设置日志输出级别

日志设置好以后可以根据设置选择哪些参与记录。这里是根据日志的级别来设置的。日志的级别分为6种、

分别是：

- TRACE：运行堆栈信息，使用率低
- DEBUG：程序员调试代码使用
- INFO：记录运维过程数据
- WARN：记录运维过程报警数据
- ERROR：记录错误堆栈信息
- FATAL：灾难信息，合并计入ERROR

一般情：开发使用DEBUG，上线使用INFO，运维信息记录使用WARN即可。下面就设置一下日志级别：

```yaml
# 开启debug模式，输出调试信息，常用于检查系统运行状况
debug: true
```

这么设置太简单粗暴了，日志系统通常都提供了细粒度的控制

```yaml
# 开启debug模式，输出调试信息，常用于检查系统运行状况
debug: true

# 设置日志级别，root表示根节点，即整体应用日志级别
logging:
	level:
    	root: debug
```

​		还可以再设置更细粒度的控制

**步骤③**：设置日志组，控制指定包对应的日志输出级别，也可以直接控制指定包对应的日志输出级别

```yaml
logging:
	# 设置日志组
    group:
    	# 自定义组名，设置当前组中所包含的包
        ebank: com.itheima.controller
    level:
    	root: warn
        # 为对应组设置日志级别
        ebank: debug
    	# 为对包设置日志级别
        com.zzx.controller: debug
```

每个包设置一下，如果感觉设置的麻烦，就先把包分个组，对组设置，没了，就这些

**总结**

1. 日志用于记录开发调试与运维过程消息
2. 日志的级别共6种，通常使用4种即可，分别是DEBUG，INFO,WARN,ERROR
3. 可以通过日志组或代码包的形式进行日志显示级别的控制



也可以使用`Lombok`来简化



## 6.1:自定义日志

SpringBoot给我们提供的默认日志

![image-20211206123431222]( https://zzx-note.oss-cn-beijing.aliyuncs.com/springboot/image-20211206123431222.png)

对于单条日志信息来，日期，触发位置，记录信息是最核心的信息

级别用于做筛选过滤，PID与线程名用于做精准分析

具体怎么改，系统学习一下日志框架，或者百度一下日志格式，自己改一下就好



## 6.3:日志文件

日志信息显示，记录已经控制住了，下面就要说一下日志的转存了。日志不能仅显示在控制台上，要把日志

记录到文件中，方便后期维护查阅。

对于日志文件的使用存在各种各样的策略，例如每日记录，分类记录，报警后记录等。这里主要研究日志文

件如何记录。

记录日志到文件中格式非常简单，设置日志文件名即可

```YAML
logging:
	file:
    	name: server.log
```

虽然使用上述格式可以将日志记录下来了，但是面对线上的复杂情况，一个文件记录肯定是不能够满足运维

要求的，通常会每天记录日志文件，同时为了便于维护，还要限制每个日志文件的大小。下面给出日志文件

的常用配置方式：

```YAML
logging:
	logback:
    	rollingpolicy:
        	max-file-size: 3KB
            file-name-pattern: server.%d{yyyy-MM-dd}.%i.log
```

以上格式是基于logback日志技术设置每日日志文件的设置格式，要求容量到达3KB以后就转存信息到第二个

文件中。文件命名规则中的%d标识日期，%i是一个递增变量，用于区分日志文件。

# 7:热部署

## 7.1:热部署原理

**非springboot项目热部署实现原理**

开发非springboot项目时，我们要制作一个web工程并通过tomcat启动，通常需要先安装tomcat服务器到磁盘中，开发的程序配置发布到安装的tomcat服务器上。如果想实现热部署的效果，这种情况其实有两种做法，一种是在tomcat服务器的配置文件中进行配置，这种做法与你使用什么IDE工具无关，不管你使用eclipse还是idea都行。还有一种做法是通过IDE工具进行配置，比如在idea工具中进行设置，这种形式需要依赖IDE工具，每款IDE工具不同，对应的配置也不太一样。但是核心思想是一样的，就是使用服务器去监控其中加载的应用，发现产生了变化就重新加载一次，`这个时候Tomcat是管理着我们的所有java对象的`

上面所说的非springboot项目实现热部署看上去是一个非常简单的过程，几乎每个小伙伴都能自己写出来。如果你不会写，我给你个最简单的思路，但是实际设计要比这复杂一些。例如启动一个定时任务，任务启动时记录每个文件的大小，以后每5秒比对一下每个文件的大小是否有改变，或者是否有新文件。如果没有改变，放行，如果有改变，刷新当前记录的文件信息，然后重新启动服务器，这就可以实现热部署了。当然，这个过程肯定不能这么做，比如我把一个打印输出的字符串"abc"改成"cba"，比对大小是没有变化的，但是内容缺实变了，所以这么做肯定不行，只是给大家打个比方，而且重启服务器这就是冷启动了，不能算热部署，领会精神吧。

看上去这个过程也没多复杂，在springboot项目中难道还有其他的弯弯绕吗？还真有



**springboot项目热部署实现原理**

基于springboot开发的web工程其实有一个显著的特征，就是tomcat服务器内置了，还记得内嵌服务器吗？服务器是以一个对象的形式在spring容器中运行的。本来我们期望于tomcat服务器加载程序后由tomcat服务器盯着程序，你变化后我就重新启动重新加载，但是`现在tomcat和我们的程序是平级`的了，都是spring容器中的组件，这下就麻烦了，缺乏了一个直接的管理权，那该怎么做呢？简单，再搞一个程序X在spring容器中盯着你原始开发的程序A不就行了吗？确实，搞一个盯着程序A的程序X就行了，如果你自己开发的程序A变化了，那么程序X就命令tomcat容器重新加载程序A就OK了。并且这样做有一个好处，spring容器中东西不用全部重新加载一遍，只需要重新加载你开发的程序那一部分就可以了，这下效率又高了，挺好。

下面就说说，怎么搞出来这么一个程序X，肯定不是我们自己手写了，springboot早就做好了，搞一个坐标导入进去就行了



**重启与重载**

一个springboot项目在运行时实际上是分两个过程进行的，根据加载的东西不同，划分成base类加载器与restart类加载器

- base类加载器：用来加载jar包中的类，jar包中的类和配置文件由于不会发生变化，因此不管加载多少次，加载的内容不会发生变化
- restart类加载器：用来加载开发者自己开发的类、配置文件、页面等信息，这一类文件受开发者影响

当springboot项目启动时，base类加载器执行，加载jar包中的信息后，restart类加载器执行，加载开发者制作的内容。`当执行构建项目后，由于jar中的信息不会变化，因此base类加载器无需再次执行`，所以`仅仅运行restart类加载即可`，也就是将开发者自己制作的内容重新加载就行了，这就完成了一次热部署的过程，`也可以说热部署的过程实际上是重新加载restart类加载器中的信息`



## 7.2:手动实现

**步骤①**：导入开发者工具对应的坐标

```XML
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <optional>true</optional>
</dependency>
```

**步骤②**：构建项目，可以使用快捷键激活此功能

![image-20220222121257218]( https://zzx-note.oss-cn-beijing.aliyuncs.com/springboot/image-20220222121257218.png)

对应的快捷键一定要记得`<CTRL>+<F9>`

以上过程就实现了springboot工程的热部署

上述过程每次进行热部署都需要开发者手工操作，不管是点击按钮还是快捷键都需要开发者手工执行



## 7.3:自动实现

自动热部署其实就是设计一个开关，打开这个开关后，IDE工具就可以自动热部署。因此这个操作和IDE工具有关，以下以idea为例设置idea中启动热部署

**步骤①**：设置自动构建项目

![image-20220222123543551]( https://zzx-note.oss-cn-beijing.aliyuncs.com/springboot/image-20220222123543551.png)

**步骤②**：允许在程序运行时进行自动构建

使用快捷键【Ctrl】+【Alt】+【Shit】+【/】打开维护面板，选择第1项【Registry...】

![image-20220222124006910]( https://zzx-note.oss-cn-beijing.aliyuncs.com/springboot/image-20220222124006910.png)

在选项中搜索comple，然后勾选对应项即可

<img src=" https://zzx-note.oss-cn-beijing.aliyuncs.com/springboot/image-20220222124240069.png" alt="image-20220222124240069" style="zoom:80%;" />

这样程序在运行的时候就可以进行自动构建了，实现了热部署的效果。

<font color="#ff0000"><b>关注</b></font>：如果你每敲一个字母，服务器就重新构建一次，这未免有点太频繁了，所以idea设置当idea工具失去焦点5秒后进行热部署。其实就是你从idea工具中切换到其他工具时进行热部署，比如改完程序需要到浏览器上去调试，这个时候idea就自动进行热部署操作

不同版本的IDEA可能具体不一样，不一样的话，具体百度



## 7.4:热部署范围

配置中`默认不参与热部署`的目录信息如下

- /META-INF/maven
- /META-INF/resources
- /resources
- /static
- /public
- /templates

如果想修改配置，可以通过application.yml文件进行设定哪些文件不参与热部署操作

```yaml
spring:
  devtools:
    restart:
      # 设置不参与热部署的文件或文件夹
      exclude: static/**,public/**,config/application.yml
```

热部署功能是一个典型的开发阶段使用的功能，到了线上环境运行程序时，这个功能就没有意义了



## 7.5:关闭热部署

线上环境运行时是不可能使用热部署功能的，所以需要强制关闭此功能，通过配置可以关闭此功能

```yaml
spring:
  devtools:
    restart:
      enabled: false
```

如果当心配置文件层级过多导致相符覆盖最终引起配置失效，可以提高配置的层级，在更高层级中配置关闭热部署。例如在启动容器前通过系统属性设置关闭热部署功能

```JAVA
@SpringBootApplication
public class SSMPApplication {
    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled","false");
        SpringApplication.run(SSMPApplication.class);
    }
}
```

其实上述担心略微有点多余，因为线上环境的维护是不可能出现修改代码的操作的，这么做唯一的作用是降低资源消耗，毕竟那双盯着你项目是不是产生变化的眼睛只要闭上了，就不具有热部署功能了，这个开关的作用就是禁用对应功能



# 	8:高级配置

## 8.1:@ConfigurationProperties

之前学习过，此注解的作用是用来为bean绑定属性的。可以在yml配置文件中以对象的格式添加若干属性

```YML
servers:
  ip-address: 192.168.0.1 
  port: 2345
  timeout: -1
```

然后开发一个用来封装数据的实体类，注意要提供属性对应的setter方法，并且让这个对象被spring接管

```JAVA
@Component
@Data
public class ServerConfig {
    private String ipAddress;
    private int port;
    private long timeout;
}
```

再使用@ConfigurationProperties注解就可以将配置中的属性值关联到开发的模型类上

```JAVA
@Component
@Data
@ConfigurationProperties(prefix = "servers")
public class ServerConfig {
    private String ipAddress;
    private int port;
    private long timeout;
}
```

这样加载对应bean的时候就可以直接加载配置属性值了。但是目前我们学的都是给自定义的bean使用这种形式加载属性值，如果是第三方的bean呢？能不能用这种形式加载属性值呢？为什么会提出这个疑问？原因就在于当前@ConfigurationProperties注解是写在类定义的上方，而第三方开发的bean源代码不是你自己书写的，你也不可能到源代码中去添加@ConfigurationProperties注解，这种问题该怎么解决呢？下面就来说说这个问题。

使用@ConfigurationProperties注解其实可以为第三方bean加载属性，格式特殊一点而已



**步骤①**：使用@Bean注解定义第三方bean

```JAVA
@Bean
public DruidDataSource datasource(){
    DruidDataSource ds = new DruidDataSource();
    return ds;
}
```

**步骤②**：在yml中定义要绑定的属性，注意datasource此时全小写

```YAML
datasource:
  driverClassName: com.mysql.cj.jdbc.Driver
```

**步骤③**：使用@ConfigurationProperties注解为第三方bean进行属性绑定，注意前缀是全小写的datasource

```JAVA
@Bean
@ConfigurationProperties(prefix = "datasource")
public DruidDataSource datasource(){
    DruidDataSource ds = new DruidDataSource();
    return ds;
}
```

操作方式完全一样，只不过@ConfigurationProperties注解不仅能添加到类上，还可以添加到方法上，添加到类上是为spring容器管理的当前类的对象绑定属性，添加到方法上是为spring容器管理的当前方法的返回值对象绑定属性，其实本质上都一样。

做到这其实就出现了一个新的问题，目前我们定义bean不是通过`类注解@Component`定义就是通过`@Bean`定义，使用@ConfigurationProperties注解可以为bean进行属性绑定，那在一个业务系统中，哪些bean通过注解@ConfigurationProperties去绑定属性了呢？因为这个注解不仅可以写在类上，还可以写在方法上，所以找起来就比较麻烦了。`为了解决这个问题，spring给我们提供了一个全新的注解，专门标注使用@ConfigurationProperties注解绑定属性的bean是哪些`。这个注解叫做`@EnableConfigurationProperties`具体如何使用呢？

**步骤①**：在配置类上开启@EnableConfigurationProperties注解，并标注要使用@ConfigurationProperties注解绑定属性的类

```java
@SpringBootApplication
@EnableConfigurationProperties(ServerConfig.class)
public class Springboot13ConfigurationApplication {
}
```

**步骤②**：在对应的类上直接使用@ConfigurationProperties进行属性绑定

```JAVA
@Data
@ConfigurationProperties(prefix = "servers")
public class ServerConfig {
    private String ipAddress;
    private int port;
    private long timeout;
}
```

有人感觉这没区别啊？注意观察，现在`绑定属性的ServerConfig类并没有声明@Component注解`。当使用

@EnableConfigurationProperties注解时，spring会默认将其标注的类定义为bean，因此无需再次声明

@Component注解了。

最后再说一个小技巧，使用@ConfigurationProperties注解时，会出现一个提示信息

![image-20220222145535749]( https://zzx-note.oss-cn-beijing.aliyuncs.com/springboot/image-20220222145535749.png)

出现这个提示后只需要添加一个坐标此提醒就消失了

```XML
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
</dependency>
```

**总结**

1. 使用@ConfigurationProperties也可以为使用@Bean声明的第三方bean绑定属性

2. 当使用@EnableConfigurationProperties声明进行属性绑定的bean后

   无需使用@Component注解再次进行bean声明



## 8.2:宽松绑定/松散绑定

什么是宽松绑定？实际上是springboot进行编程时人性化设计的一种体现，即配置文件中的命名格式与变量名的命名格式可以进行格式上的最大化兼容。兼容到什么程度呢？几乎主流的命名格式都支持，例如：

在ServerConfig中的ipAddress属性名

```JAVA
@Component
@Data
@ConfigurationProperties(prefix = "servers")
public class ServerConfig {
    private String ipAddress;
}
```

可以与下面的配置属性名规则全兼容

```YML
servers:
  ipAddress: 192.168.0.2       # 驼峰模式
  ip_address: 192.168.0.2      # 下划线模式
  ip-address: 192.168.0.2      # 烤肉串模式
  IP_ADDRESS: 192.168.0.2      # 常量模式
```

以上4种模式最终都可以匹配到ipAddress这个属性名。为什么这样呢？原因就是在进行匹配时，配置中的名称要去掉中划线和下划线后，忽略大小写的情况下去与java代码中的属性名进行忽略大小写的等值匹配，以上4种命名去掉下划线中划线忽略大小写后都是一个词ipaddress，java代码中的属性名忽略大小写后也是ipaddress，这样就可以进行等值匹配了，这就是为什么这4种格式都能匹配成功的原因。不过`springboot官方推荐使用烤肉串模式`，也就是中划线模式。即使用-分隔，使用小写字母数字作为标准字符，且必须以字母开头。然后再看我们写的名称dataSource，就不满足上述要求

**注意：**以上规则仅针对springboot中@ConfigurationProperties注解进行属性绑定时有效，对@Value注解进行属性映射无效

## 8.3:常用计量单位绑定

假设线上服务器完成一次主从备份，配置超时时间240，这个240如果`单位是秒就是超时时间4分钟`，如果`单位是分钟就是超时时间4小时`。面对一次线上服务器的主从备份，设置4分钟，简直是开玩笑，别说拷贝过程，备份之前的压缩过程4分钟也搞不定，这个时候问题就来了，`怎么解决这个误会？`

除了加强约定之外，springboot充分利用了`JDK8`中提供的全新的用来表示计量单位的新数据类型，从根本上解决这个问题。以下模型类中添加了两个JDK8中新增的类，分别是Duration和DataSize



### 1:Duration

表示时间间隔，可以通过@DurationUnit注解描述时间单位

例如上例中描述的单位为小时（ChronoUnit.HOURS）

常用单位如下：



<img src=" https://zzx-note.oss-cn-beijing.aliyuncs.com/springboot/image-20220222173911102.png" alt="image-20220222173911102"  />

### 2:DataSize

表示存储空间，可以通过@DataSizeUnit注解描述存储空间单位

例如上例中描述的单位为MB（DataUnit.MEGABYTES）

常用单位如下：

<img src=" https://zzx-note.oss-cn-beijing.aliyuncs.com/springboot/image-20220222174130102.png" alt="image-20220222174130102"  />



## 8.4:校验

代码中需要int类型，配置中给了非法的数值，例如写一个“a"，这种数据肯定无法有效的绑定，还会引发错误。SpringBoot给出了强大的数据校验功能，可以有效的避免此类问题的发生。在`JAVAEE的JSR303`规范中给出了具体的数据校验标准，`开发者可以根据自己的需要选择对应的校验框架`，此处使用Hibernate提供的校验框架来作为实现进行数据校验。`书写应用格式非常固定`，话不多说，直接上步骤

**步骤①**：开启校验框架

```xml
<!--1.导入JSR303规范-->
<dependency>
    <groupId>javax.validation</groupId>
    <artifactId>validation-api</artifactId>
</dependency>
<!--使用hibernate框架提供的校验器做实现-->
<dependency>
    <groupId>org.hibernate.validator</groupId>
    <artifactId>hibernate-validator</artifactId>
</dependency>
```

**步骤②**：在需要开启校验功能的`类上使用注解@Validated`开启校验功能

```java
@Component
@Data
@ConfigurationProperties(prefix = "servers")
//开启对当前bean的属性注入校验
@Validated
public class ServerConfig {
}
```

**步骤③**：`对具体的字段设置校验规则`

```JAVA
@Component
@Data
@ConfigurationProperties(prefix = "servers")
//开启对当前bean的属性注入校验
@Validated
public class ServerConfig {
    //设置具体的规则
    @Max(value = 8888,message = "最大值不能超过8888")
    @Min(value = 202,message = "最小值不能低于202")
    private int port;
}
```

通过设置数据格式校验，就可以有效避免非法数据加载，基本上就是一个格式



## 8.5:一个错误

先把问题描述一下，这位开发者连接数据库正常操作，但是运行程序后显示的信息是密码错误。

```CMD
java.sql.SQLException: Access denied for user 'root'@'localhost' (using password: YES)
```

其实看到这个报错，几乎所有的学习者都能分辨出来，这是用户名和密码不匹配，就就是密码输入错了，但是问题就在于密码并没有输入错误，这就比较讨厌了

来看看用户名密码的配置是如何写的：

```YAML
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/ssm_db?serverTimezone=UTC
    username: root
    password: 0127
```

这名开发者的生日是1月27日，所以密码就使用了0127，其实问题就出在这里了

0127在开发者眼中是一个字符串“0127”，但是在springboot看来，这就是一个数字，而且是一个八进制的数字。当后台使用String类型接收数据时，如果配置文件中配置了一个整数值，他是先按照整数进行处理，读取后再转换成字符串。巧了，0127撞上了八进制的格式，所以最终以十进制数字87的结果存在了，最后处理的就是字符串"87"

两个注意点

- 第一，字符串标准书写加上引号包裹，养成习惯
- 第二，遇到0开头的数据多注意



# 9:测试

## 9.1:加载测试专用属性

测试过程本身并不是一个复杂的过程，但是很多情况下测试时需要模拟一些线上情况，或者模拟一些特殊情况。如果当前环境按照线上环境已经设定好了，例如是下面的配置

```YAML
env:
  maxMemory: 32GB
  minMemory: 16GB
```

但是你现在想测试对应的兼容性，需要测试如下配置

```YAML
env:
  maxMemory: 16GB
  minMemory: 8GB
```

这个时候我们能不能每次测试的时候都去修改源码application.yml中的配置进行测试呢？显然是不行的。每次测试前改过来，每次测试后改回去，这太麻烦了。于是我们就想，需要在测试环境中创建一组临时属性，去覆盖源码中设定的属性，这样测试用例就相当于是一个独立的环境，能够独立测试，这样就方便多了。

**临时属性**

springboot已经为我们开发者早就想好了这种问题该如何解决，并且提供了对应的功能入口。在测试用例程序中，可以通过对注解@SpringBootTest添加属性来模拟临时属性，具体如下：

```JAVA
//properties属性可以为当前测试用例添加临时的属性配置
@SpringBootTest(properties = {"test.prop=testValue1"})
public class PropertiesAndArgsTest {

    @Value("${test.prop}")
    private String msg;
    
    @Test
    void testProperties(){
        System.out.println(msg);
    }
}
```

使用注解@SpringBootTest的properties属性就可以为当前测试用例添加临时的属性，覆盖源码配置文件中对应的属性值进行测试。这个时候就不会读取配置文件的信息了，msg的值就是 `testValue1`



**临时参数**

除了上述这种情况，在前面讲解使用命令行启动springboot程序时讲过，通过命令行参数也可以设置属性值。而且线上启动程序时，通常都会添加一些专用的配置信息。作为运维人员他们才不懂java，更不懂这些配置的信息具体格式该怎么写，那如果我们作为开发者提供了对应的书写内容后，能否提前测试一下这些配置信息是否有效呢？当时是可以的，还是通过注解@SpringBootTest的另一个属性来进行设定。

```JAVA
//args属性可以为当前测试用例添加临时的命令行参数
@SpringBootTest(args={"--test.prop=testValue2"})
public class PropertiesAndArgsTest {
    
    @Value("${test.prop}")
    private String msg;
    
    @Test
    void testProperties(){
        System.out.println(msg);
    }
}
```

使用注解@SpringBootTest的args属性就可以为当前测试用例模拟命令行参数并进行测试

这个就类似 java -jar xxx.jar --test.prop=testValue2

args属性配置优先于properties属性配置加载



## 9.2:加载测试专用配置

现在我们的需求就是在测试环境中再添加一个配置类，然后启动测试环境时，生效此配置就行了。其实做法和spring环境中加载多个配置信息的方式完全一样。具体操作步骤如下：

**步骤①**：在测试包test中创建专用的测试环境配置类

```java
@Configuration
public class MsgConfig {
    @Bean
    public String msg(){
        return "bean msg";
    }
}
```

上述配置仅用于演示当前实验效果，实际开发可不能这么注入String类型的数据

**步骤②**：在启动测试环境时，导入测试环境专用的配置类，使用@Import注解即可实现

```java
@SpringBootTest
@Import({MsgConfig.class})
public class ConfigurationTest {

    @Autowired
    private String msg;

    @Test
    void testConfiguration(){
        System.out.println(msg);
    }
}
```

到这里就通过@Import属性实现了基于开发环境的配置基础上，对配置进行测试环境的追加操作，实现了1+1的配置环境效果。这样我们就可以实现每一个不同的测试用例加载不同的bean的效果，丰富测试用例的编写，同时不影响开发环境的配置



## 9.3:Web环境模拟测试

在测试中对表现层功能进行测试需要一个基础和一个功能。所谓的一个基础是运行测试程序时，必须启动web环境，不然没法测试web功能。一个功能是必须在测试程序中具备发送web请求的能力，不然无法实现web功能的测试。所以在测试用例中测试表现层接口这项工作就转换成了两件事，一，如何在测试类中启动web测试，二，如何在测试类中发送web请求。下面一件事一件事进行，先说第一个

### 1:测试类中启动web环境

每一个springboot的测试类上方都会标准@SpringBootTest注解，而注解带有一个属性，叫做webEnvironment。通过该属性就可以设置在测试用例中启动web环境，具体如下：

```JAVA
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebTest {	
}
```

测试类中启动web环境时，可以指定启动的Web环境对应的端口，springboot提供了4种设置值，分别如下：

<img src=" https://zzx-note.oss-cn-beijing.aliyuncs.com/springboot/image-20220223125453317.png" alt="image-20220223125453317"  />

- MOCK：根据当前设置确认是否启动web环境，例如使用了Servlet的API就启动web环境，属于适配性的配置
- DEFINED_PORT：使用自定义的端口作为web服务器端口
- RANDOM_PORT：使用随机端口作为web服务器端口
- NONE：不启动web环境

通过上述配置，现在启动测试程序时就可以正常启用web环境了，建议大家测试时使用RANDOM_PORT，避免代码中因为写死设定引发线上功能打包测试时由于端口冲突导致意外现象的出现。就是说你程序中写了用8080端口，结果线上环境8080端口被占用了，结果你代码中所有写的东西都要改，这就是写死代码的代价。现在你用随机端口就可以测试出来你有没有这种问题的隐患了。

测试环境中的web环境已经搭建好了，下面就可以来解决第二个问题了，如何在程序代码中发送web请求



### 2:测试类中发送请求

对于测试类中发送请求，其实java的API就提供对应的功能，只不过平时各位小伙伴接触的比较少，所以较为陌生。springboot为了便于开发者进行对应的功能开发，对其又进行了包装，简化了开发步骤，具体操作如下：

**步骤①**：在测试类中开启web虚拟调用功能，通过注解@AutoConfigureMockMvc实现此功能的开启

```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//开启虚拟MVC调用
@AutoConfigureMockMvc
public class WebTest {
}
```

**步骤②**：定义发起虚拟调用的对象MockMVC，通过自动装配的形式初始化对象，由Springboot创建这个对象

```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//开启虚拟MVC调用
@AutoConfigureMockMvc
public class WebTest {

    @Test
    void testWeb(@Autowired MockMvc mvc) {
    }
}
```

**步骤③**：创建一个虚拟请求对象，封装请求的路径，并使用MockMVC对象发送对应请求

```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//开启虚拟MVC调用
@AutoConfigureMockMvc
public class WebTest {

    @Test
    void testWeb(@Autowired MockMvc mvc) throws Exception {
        //http://localhost:8080/books
        //创建虚拟请求，当前访问/books
        //不需要写ip+port
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books");
        //执行对应的请求
        mvc.perform(builder);
    }
}
```

执行测试程序，现在就可以发送/books对应的请求了，注意访问路径不要写http://localhost:8080/books，因为前面的服务器IP地址和端口使用的是当前虚拟的web环境，无需指定，仅指定请求的具体路径即可。



**思考**

目前已经成功的发送了请求，但是还没有起到测试的效果，测试过程必须出现预计值与真实值的比对结果才能确认测试结果是否通过，虚拟请求中能对哪些请求结果进行比对呢？



### 3:web环境请求结果比对

发完请求得到的信息只有一种，就是响应对象。至于响应对象中包含什么，就可以比对什么。常见的比对内容如下：

- 响应状态匹配

  ```JAVA
  @Test
  void testStatus(@Autowired MockMvc mvc) throws Exception {
      MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books");
      ResultActions action = mvc.perform(builder);
      //设定预期值 与真实值进行比较，成功测试通过，失败测试失败
      //定义本次调用的预期值
      StatusResultMatchers status = MockMvcResultMatchers.status();
      //预计本次调用时成功的：状态200
      ResultMatcher ok = status.isOk();
      //添加预计值到本次调用过程中进行匹配
      action.andExpect(ok);
  }
  ```

- 响应体匹配（非json数据格式）

  ```java
  @Test
  void testBody(@Autowired MockMvc mvc) throws Exception {
      MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books");
      ResultActions action = mvc.perform(builder);
      //设定预期值 与真实值进行比较，成功测试通过，失败测试失败
      //定义本次调用的预期值
      ContentResultMatchers content = MockMvcResultMatchers.content();
      ResultMatcher result = content.string("springboot2");
      //添加预计值到本次调用过程中进行匹配
      action.andExpect(result);
  }
  ```

- 响应体匹配（json数据格式，开发中的主流使用方式）

  ```JAVA
  @Test
  void testJson(@Autowired MockMvc mvc) throws Exception {
      MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books");
      ResultActions action = mvc.perform(builder);
      //设定预期值 与真实值进行比较，成功测试通过，失败测试失败
      //定义本次调用的预期值
      ContentResultMatchers content = MockMvcResultMatchers.content();
      ResultMatcher result = content.json("{\"id\":1,\"name\":\"springboot2\",\"type\":\"springboot\"}");
      //添加预计值到本次调用过程中进行匹配
      action.andExpect(result);
  }
  ```

- 响应头信息匹配

  ```JAVA
  @Test
  void testContentType(@Autowired MockMvc mvc) throws Exception {
      MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books");
      ResultActions action = mvc.perform(builder);
      //设定预期值 与真实值进行比较，成功测试通过，失败测试失败
      //定义本次调用的预期值
      HeaderResultMatchers header = MockMvcResultMatchers.header();
      ResultMatcher contentType = header.string("Content-Type", "application/json");
      //添加预计值到本次调用过程中进行匹配
      action.andExpect(contentType);
  }
  ```

- 基本上齐了，头信息，正文信息，状态信息都有了，就可以组合出一个完美的响应结果比对结果了。以下范例就是三种信息同时进行匹配校验，也是一个完整的信息匹配过程

  ```java
  @Test
  void testGetById(@Autowired MockMvc mvc) throws Exception {
      MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/books");
      ResultActions action = mvc.perform(builder);
  
      StatusResultMatchers status = MockMvcResultMatchers.status();
      ResultMatcher ok = status.isOk();
      action.andExpect(ok);
  
      HeaderResultMatchers header = MockMvcResultMatchers.header();
      ResultMatcher contentType = header.string("Content-Type", "application/json");
      action.andExpect(contentType);
  
      ContentResultMatchers content = MockMvcResultMatchers.content();
      ResultMatcher result = content.json("{\"id\":1,\"name\":\"springboot\",\"type\":\"springboot\"}");
      action.andExpect(result);
  }
  ```

## 9.4:数据层测试回滚

当前我们的测试程序可以完美的进行表现层、业务层、数据层接口对应的功能测试了，但是测试用例开发完成后，`在打包的阶段由于test生命周期属于必须被运行的生命周期，如果跳过会给系统带来极高的安全隐患，所以测试用例必须执行`。但是新的问题就呈现了，`测试用例如果测试时产生了事务提交就会在测试过程中对数据库数据产生影响，进而产生垃圾数据。这个过程不是我们希望发生的`，作为开发者测试用例该运行运行，但是过程中产生的数据不要在我的系统中留痕，这样该如何处理呢？

Springboot早就为开发者想到了这个问题，并且针对此问题给出了最简解决方案，在原始测试用例中添加注解@Transactional即可实现当前测试用例的事务不提交。当程序运行后，只要注解@Transactional出现的位置存在注解@SpringBootTest，springboot就会认为这是一个测试程序，无需提交事务，所以也就可以避免事务的提交。

```JAVA
@SpringBootTest
@Transactional
@Rollback(true)
public class DaoTest {
    @Autowired
    private BookService bookService;

    @Test
    void testSave(){
        Book book = new Book();
        book.setName("springboot3");
        book.setType("springboot3");
        book.setDescription("springboot3");

        bookService.save(book);
    }
}
```

如果开发者想提交事务，也可以，再添加一个@RollBack的注解，设置回滚状态为false即可正常提交事务

**总结**

1. 在springboot的测试类中通过添加注解@Transactional来阻止测试用例提交事务
2. 通过注解@Rollback控制springboot测试类执行结果是否提交事务，需要配合注解@Transactional使用

## 9.5:测试用例数据设定

springboot提供了在配置中使用随机值的机制，确保每次运行程序加载的数据都是随机的。具体如下：

```yaml
testcase:
  book:
    id: ${random.int}
    id2: ${random.int(10)}
    type: ${random.int!5,10!}
    name: ${random.value}
    uuid: ${random.uuid}
    publishTime: ${random.long}
```

当前配置就可以在每次运行程序时创建一组随机数据，避免每次运行时数据都是固定值的尴尬现象发生，有助于测试功能的进行。数据的加载按照之前加载数据的形式，使用@ConfigurationProperties注解即可

```JAVA
@Component
@Data
@ConfigurationProperties(prefix = "testcase.book")
public class BookCase {
    private int id;
    private int id2;
    private int type;
    private String name;
    private String uuid;
    private long publishTime;
}
```

对于随机值的产生，还有一些小的限定规则，比如产生的数值性数据可以设置范围等，具体如下：

<img src=" https://zzx-note.oss-cn-beijing.aliyuncs.com/springboot/image-20220223135454862.png" alt="image-20220223135454862" style="zoom:80%;" />

- ${random.int}表示随机整数
- ${random.int(10)}表示10以内的随机数
- ${random.int(10,20)}表示10到20的随机数
- 其中()可以是任意字符，例如[]，!!均可



# 10:数据层解决方案

## 10.1:SQL

现在数据层解决方案可以说是Mysql+Druid+MyBatisPlus/Mybatis

而三个技术分别对应了数据层操作的三个层面：

- 数据源技术：Druid
- 持久化技术：MyBatisPlus/Mybatis
- 数据库技术：MySQL

下面的研究就分为三个层面进行研究，对应上面列出的三个方面，咱们就从第一个数据源技术开始说起



### 1:数据源技术

目前我们使用的数据源技术是Druid，运行时可以在日志中看到对应的数据源初始化信息，具体如下：

```CMD
INFO 28600 --- [           main] c.a.d.s.b.a.DruidDataSourceAutoConfigure : Init DruidDataSource
INFO 28600 --- [           main] com.alibaba.druid.pool.DruidDataSource   : {dataSource-1} inited
```

如果不使用Druid数据源，程序运行后是什么样子呢？是独立的数据库连接对象还是有其他的连接池技术支持呢？将Druid技术对应的starter去掉再次运行程序可以在日志中找到如下初始化信息：

```CMD
INFO 31820 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
INFO 31820 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
```

我们发现日志中有HikariDataSource这个信息，就算不懂这是个什么技术，看名字也能看出来，以DataSource结尾的名称，这一定是一个数据源技术。我们又没有手工添加这个技术，这个技术哪里来的呢？这就是这一节要讲的知识，springboot内嵌数据源。

springboot根据开发者的习惯出发，开发者提供了数据源技术就用提供的，开发者没有提供，那总不能手工管理一个一个的数据库连接对象啊，怎么办？我给你一个默认的就好了，这样省心又省事，大家都方便

springboot提供了3款内嵌数据源技术，分别如下：

- HikariCP【默认】
- Tomcat提供DataSource
- Commons DBCP



第一种：HikartCP，这是springboot官方推荐的数据源技术，作为默认内置数据源使用

第二种：Tomcat提供的DataSource，如果不想用HikartCP，并且使用tomcat作为web服务器进行web程序的开发，默认就使用这个。为什么是Tomcat，不是其他web服务器呢？因为web技术导入starter后，默认使用内嵌tomcat，既然都是默认使用的技术了，那就一用到底，数据源也用它的。怎么才能不使用HikartCP用tomcat提供的默认数据源对象呢？把HikartCP技术的坐标排除掉就OK了。

第三种：DBCP，这个使用的条件就更苛刻了，既不使用HikartCP也不使用tomcat的DataSource时，默认给你用这个。

springboot就怕你自己管不好连接对象，给你一顿推荐，真是开发界的最强辅助

之前我们配置druid时使用druid的starter对应的配置如下：

```YAML
spring:
  datasource:
    druid:	
   	  url: jdbc:mysql://localhost:3306/ssm_db?serverTimezone=UTC
      driver-class-name: com.mysql.cj.jdbc.Driver
      username: root
      password: root
```

换成是默认的数据源HikariCP后，直接吧druid删掉就行了，如下：

```YAML
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/ssm_db?serverTimezone=UTC
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: root
```

当然，也可以写上是对hikari做的配置，但是url地址要单独配置，如下：

```YAML
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/ssm_db?serverTimezone=UTC
    hikari:
      driver-class-name: com.mysql.cj.jdbc.Driver
      username: root
      password: root
```

这就是配置hikari数据源的方式。如果想对hikari做进一步的配置，可以继续配置其独立的属性。例如：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/ssm_db?serverTimezone=UTC
    hikari:
      driver-class-name: com.mysql.cj.jdbc.Driver
      username: root
      password: root
      maximum-pool-size: 50
```

如果不想使用hikari数据源，使用tomcat的数据源或者DBCP配置格式也是一样的。学习到这里，以后我们做数据层时，数据源对象的选择就不再是单一的使用druid数据源技术了，可以根据需要自行选择



### 2:持久化技术

springboot充分发挥其最强辅助的特征，给开发者提供了一套现成的数据持久层技术，叫做JdbcTemplate

其实这个技术不能说是springboot提供的，因为不使用springboot技术，一样能使用它，谁提供的呢？spring

技术提供的，所以在springboot技术范畴中，这个技术也是存在的，毕竟springboot技术是加速spring程序开

发而创建的。JdbcTemplate默认就在Spring容器，直接注入就行

这个技术其实就是回归到jdbc最原始的编程形式来进行数据层的开发，下面直接上操作步骤：

**步骤①**：导入jdbc对应的坐标，记得是starter

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency
```

**步骤②**：自动装配JdbcTemplate对象

```java
@SpringBootTest
class Springboot15SqlApplicationTests {
    @Test
    void testJdbcTemplate(@Autowired JdbcTemplate jdbcTemplate){
    }
}
```

**步骤③**：使用JdbcTemplate实现查询操作（非实体类封装数据的查询操作）

```java
@Test
void testJdbcTemplate(@Autowired JdbcTemplate jdbcTemplate){
    String sql = "select * from tbl_book";
    List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
    System.out.println(maps);
}
```

**步骤④**：使用JdbcTemplate实现查询操作（实体类封装数据的查询操作）

```java
@Test
void testJdbcTemplate(@Autowired JdbcTemplate jdbcTemplate){

    String sql = "select * from tbl_book";
    RowMapper<Book> rm = new RowMapper<Book>() {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book temp = new Book();
            temp.setId(rs.getInt("id"));
            temp.setName(rs.getString("name"));
            temp.setType(rs.getString("type"));
            temp.setDescription(rs.getString("description"));
            return temp;
        }
    };
    List<Book> list = jdbcTemplate.query(sql, rm);
    System.out.println(list);
}
```

**步骤⑤**：使用JdbcTemplate实现增删改操作

```java
@Test
void testJdbcTemplateSave(@Autowired JdbcTemplate jdbcTemplate){
    String sql = "insert into tbl_book values(3,'springboot1','springboot2','springboot3')";
    jdbcTemplate.update(sql);
}
```

如果想对JdbcTemplate对象进行相关配置，可以在yml文件中进行设定，具体如下：

```yaml
spring:
  jdbc:
    template:
      query-timeout: -1   # 查询超时时间
      max-rows: 500       # 最大行数
      fetch-size: -1      # 缓存行数
```

**总结**

1. SpringBoot内置JdbcTemplate持久化解决方案
2. 使用JdbcTemplate需要导入spring-boot-starter-jdbc的坐标



### 3:数据库技术

springboot提供了3款内置的数据库，分别是

- H2
- HSQL
- Derby

以上三款数据库除了可以独立安装之外，还可以像是tomcat服务器一样，采用内嵌的形式运行在spirngboot容器中。内嵌在容器中运行，那必须是java对象啊，对，这三款数据库底层都是使用java语言开发的。

我们一直使用MySQL数据库就挺好的，为什么有需求用这个呢？原因就在于这三个数据库都可以采用内嵌容器的形式运行，在应用程序运行后，如果我们进行测试工作，此时测试的数据无需存储在磁盘上，但是又要测试使用，内嵌数据库就方便了，运行在内存中，该测试测试，该运行运行，等服务器关闭后，一切烟消云散，多好，省得你维护外部数据库了。这也是内嵌数据库的最大优点，方便进行功能测试。

下面以H2数据库为例讲解如何使用这些内嵌数据库，操作步骤也非常简单，简单才好用嘛

**步骤①**：导入H2数据库对应的坐标，一共2个

```xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

**步骤②**：将工程设置为web工程，启动工程时启动H2数据库

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

**步骤③**：通过配置开启H2数据库控制台访问程序，也可以使用其他的数据库连接软件操作

```yaml
spring:
  h2:
    console:
      enabled: true
      path: /h2
```

web端访问路径/h2，访问密码123456，如果访问失败，先配置下列数据源，启动程序运行后再次访问/h2路径就可以正常访问了

```yaml
datasource:
  url: jdbc:h2:~/test
  hikari:
    driver-class-name: org.h2.Driver
    username: sa
    password: 123456
```

**步骤④**：使用JdbcTemplate或MyBatisPlus技术操作数据库

其实我们只是换了一个数据库而已，其他的东西都不受影响。一个重要提醒，别忘了，上线时，把内存级数

据库关闭，采用MySQL数据库作为数据持久化方案，关闭方式就是设置enabled属性为false即可。

**总结**

1. H2内嵌式数据库启动方式，添加坐标，添加配置
2. H2数据库线上运行时请务必关闭



现在的可选技术就丰富的多了。

- 数据源技术：Druid、Hikari、tomcat DataSource、DBCP
- 持久化技术：MyBatisPlus、MyBatis、JdbcTemplate
- 数据库技术：MySQL、H2、HSQL、Derby

现在开发程序时就可以在以上技术中任选一种组织成一套数据库解决方案了



## 10.2:NoSQL

NoSQL就是非关系型数据库解决方案，意思就是数据该存存该取取，只是这些数据不放在关系型数据库中了，那放在哪里？自然是一些能够存储数据的其他相关技术中了，比如Redis等



### 1:整合Redis

整合之前先梳理一下整合的思想，springboot整合任何技术其实就是在springboot中使用对应技术的API。如果两个技术没有交集，就不存在整合的概念了。所谓整合其实就是使用springboot技术去管理其他技术，几个问题是躲不掉的。

第一，需要先导入对应技术的坐标，而整合之后，这些坐标都有了一些变化，使用starter来帮助我们

第二，任何技术通常都会有一些相关的设置信息，整合之后，这些信息如何写，写在哪是一个问题

第三，整合之后如果没有给开发者带来一些便捷操作，那整合将毫无意义，所以整合后操作肯定要简化一些，那对应的操作方式自然也有所不同

按照上面的三个问题去思考springboot整合所有技术是一种通用思想，在整合的过程中会逐步摸索出整合的套路，而且适用性非常强，经过若干种技术的整合后基本上可以总结出一套固定思维。

**步骤①**：导入springboot整合redis的starter坐标

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

**步骤②**：进行基础配置

```yaml
spring:
  redis:
    host: localhost
    port: 6379
```

操作redis，最基本的信息就是操作哪一台redis服务器，所以服务器地址属于基础配置信息，不可缺少。但是即便你不配置，目前也是可以用的。因为以上两组信息都有默认配置，刚好就是上述配置值。

**步骤③**：使用springboot整合redis的专用客户端接口操作，此处使用的是RedisTemplate

```java
@SpringBootTest
class Springboot16RedisApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;
    
    //使用API
}

```

在操作redis时，需要先确认操作何种数据，根据数据种类得到操作接口。例如使用opsForValue()获取string类型的数据操作接口，使用opsForHash()获取hash类型的数据操作接口，剩下的就是调用对应api操作了



**使用StringRedisTemplate**

由于redis内部不提供java对象的存储格式，因此当操作的数据以对象的形式存在时，会进行转码，转换成字符串格式后进行操作。为了方便开发者使用基于字符串为数据的操作，springboot整合redis时提供了专用的API接口StringRedisTemplate，你可以理解为这是RedisTemplate的一种指定数据泛型的操作API

```JAVA
@SpringBootTest
public class StringRedisTemplateTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    void get(){
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String name = ops.get("name");
        System.out.println(name);
    }
}
```

**redis客户端选择**

 	Springboot整合redis技术提供了多种客户端兼容模式，默认提供的是lettucs客户端技术，也可以根据需要切换成指定客户端技术，例如jedis客户端技术，切换成jedis客户端技术操作步骤如下：

**步骤①**：导入jedis坐标

```xml
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
</dependency>
```

**步骤②**：配置客户端技术类型，设置为jedis

```yaml
spring:
  redis:
    host: localhost
    port: 6379
    client-type: jedis
```

**步骤③**：根据需要设置对应的配置

```yaml
spring:
  redis:
    host: localhost
    port: 6379
    client-type: jedis
    lettuce:
      pool:
        max-active: 16
    jedis:
      pool:
        max-active: 16
```

**lettcus与jedis区别**

- jedis连接Redis服务器是直连模式，`多线程模式下使用jedis会存在线程安全问题`，解决方案可以通过配置连接池使每个连接专用，这样整体性能就大受影响
- lettcus基于Netty框架进行与Redis服务器连接，底层设计中采用StatefulRedisConnection。 StatefulRedisConnection自身是线程安全的，可以保障并发访问安全问题，所以一个连接可以被多线程复用。当然lettcus也支持多连接实例一起工作

**总结**

1. springboot整合redis提供了StringRedisTemplate对象，以字符串的数据格式操作redis
2. 如果需要切换redis客户端实现技术，可以通过配置的形式进行



### 2:整合MongoDB

Redis的数据格式单一性，无法操作结构化数据，当操作对象型的数据时，Redis就显得捉襟见肘。在保障访问速度的情况下，如果想操作结构化数据，看来Redis无法满足要求了，此时需要使用全新的数据存储结束来解决此问题，本节讲解springboot如何整合MongoDB技术

MongoDB是一个开源、高性能、无模式的文档型数据库，它是NoSQL数据库产品中的一种，是最像关系型数据库的非关系型数据库

什么叫无模式呢？简单说就是作为一款数据库，没有固定的数据存储结构，第一条数据可能有A、B、C一共3个字段，第二条数据可能有D、E、F也是3个字段，第三条数据可能是A、C、E3个字段，也就是说数据的结构不固定，这就是无模式。有人会说这有什么用啊？灵活，随时变更，不受约束。基于上述特点，MongoDB的应用面也会产生一些变化。以下列出了一些可以使用MongoDB作为数据存储的场景，但是并不是必须使用MongoDB的场景：

- 淘宝用户数据
  - 存储位置：数据库
  - 特征：永久性存储，修改频度极低
- 游戏装备数据、游戏道具数据
  - 存储位置：数据库、Mongodb
  - 特征：永久性存储与临时存储相结合、修改频度较高
- 直播数据、打赏数据、粉丝数据
  - 存储位置：数据库、Mongodb
  - 特征：永久性存储与临时存储相结合，修改频度极高
- 物联网数据
  - 存储位置：Mongodb
  - 特征：临时存储，修改频度飞速



**步骤①**：导入springboot整合MongoDB的starter坐标

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
```

**步骤②**：进行基础配置

```yaml
spring:
  data:
    mongodb:
      uri: mongodb://localhost/itheima

#也可以这样
spring:
  data:
    mongodb:
      host: 192.168.61.133
      port: 27017
      database: articledb    
```

**步骤③**：使用springboot整合MongoDB的专用客户端接口MongoTemplate来进行操作

```java
@SpringBootTest
class Springboot17MongodbApplicationTests {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Test
    void contextLoads() {
        Book book = new Book();
        book.setId(2);
        book.setName("springboot2");
        book.setType("springboot2");
        book.setDescription("springboot2");
        mongoTemplate.save(book);
    }
    @Test
    void find(){
        List<Book> all = mongoTemplate.findAll(Book.class);
        System.out.println(all);
    }
}
```

### 3:整合ES

**步骤①**：导入springboot整合ES的starter坐标

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
</dependency>
```

**步骤②**：进行基础配置

```yaml
spring:
  elasticsearch:
    rest:
      uris: http://localhost:9200
```

**步骤③**：使用springboot整合ES的专用客户端接口ElasticsearchRestTemplate来进行操作

```java
@SpringBootTest
class Springboot18EsApplicationTests {
    @Autowired
    private ElasticsearchRestTemplate template;
}
```

上述操作形式是ES早期的操作方式，使用的客户端被称为`Low Level Client`，这种客户端操作方式性能方面略显不足，于是ES开发了全新的客户端操作方式，称为High Level Client。高级别客户端与ES版本同步更新，但是springboot最初整合ES的时候使用的是低级别客户端，所以企业开发需要更换成高级别的客户端模式。

下面使用高级别客户端方式进行springboot整合ES，操作步骤如下：

**步骤①**：导入springboot整合ES高级别客户端的坐标，`此种形式目前没有对应的starter`

```xml
<dependency>
    <groupId>org.elasticsearch.client</groupId>
    <artifactId>elasticsearch-rest-high-level-client</artifactId>
</dependency>
```

**步骤②**：使用编程的形式设置连接的ES服务器，并获取客户端对象

```java
@SpringBootTest
class Springboot18EsApplicationTests {
    private RestHighLevelClient client;
      @Test
      void testCreateClient() throws IOException {
          HttpHost host = HttpHost.create("http://localhost:9200");
          RestClientBuilder builder = RestClient.builder(host);
          client = new RestHighLevelClient(builder);
          client.close();
      }
}
```

**步骤③**：使用客户端对象操作ES，例如创建索引

```java
@SpringBootTest
class Springboot18EsApplicationTests {
    private RestHighLevelClient client;
      @Test
      void testCreateIndex() throws IOException {
          HttpHost host = HttpHost.create("http://localhost:9200");
          RestClientBuilder builder = RestClient.builder(host);
          client = new RestHighLevelClient(builder);

          CreateIndexRequest request = new CreateIndexRequest("books");
          client.indices().create(request, RequestOptions.DEFAULT); 
          client.close();
      }
}
```