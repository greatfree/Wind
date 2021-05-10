# Wind

GreatFree as a Generic Distributed Programming Paradigm for the Internet-Oriented Computing Environment

Bing Li

Abstract

GreatFree is a generic distributed programming paradigm for the Internet-oriented computing environment. For the complexity of the particular circumstance, three technologies, i.e., the Distributed Primitives (DP), the Application-level Threading on Messaging (ATM) and the Distributed Common Patterns (DCP), are proposed to ensure its generality and rapidness. The DP is made up with the distributed primitive application programming interfaces as well as the corresponding distributed primitive code-level design patterns. Any distributed components and systems are originated from those primitives. To support sufficient adaptability to various distributed contexts, the ATM provides developers with the asynchronous message-passing based threading technique to implement high-quality distributed concurrent algorithms. The DCP unveils that the code of heterogenous distributed systems is constructed with the homogeneous code-level design patterns. The discovery not only speeds up the development of distributed systems but also establishes the basis of the first generic distributed programming language for the Internet-oriented computing environment upon GreatFree.

1. Introduction

GreatFree is a generic programming paradigm for the Internet-oriented computing environment. Because of the complexity of the particular circumstance, three technologies, including the Distributed Primitives (DP), the Application-level Threading on Messaging (ATM) and the Distributed Common Patterns (DCP), are proposed to ensure the generality and the rapidness of GreatFree. The DP is made up with the Distributed Primitive Application (DPA) programming interfaces as well as the corresponding Distributed Primitive code-level design patterns or Idioms (DPI). When programming with GreatFree, any distributed components and systems are originated from the DP. The ATM provides developers with the asynchronous message-passing threading to implement fine-grained high-quality distributed concurrent algorithms. The DCP unveils that the code of heterogeneous distributed systems is constructed with the homogeneous code-level design patterns. The discovery not only speeds up the development of distributed systems but also establishes the basis of the first generic distributed programming language for the Internet-oriented computing environment upon GreatFree.

The DP answers the question what the most fundamental components look like exactly when programming distributed systems. As one of the foundations of GreatFree, the DP is identical to the abstraction of general-purpose techniques for distributed systems development. Such an abstraction shapes GreatFree to become a full coverage and rapid development technique in a sense that it not only hides tedious details but also exposes indispensable elements. The DP includes two subsets of programming components, i.e., the DPA and the DPI. The DPA supports a couple of rudimentary distributed functions and the DPI provides the stable structures to assemble the DPA into high-quality code.

The DPA represents the most basic and mandatory distributed computing resources and mechanisms to construct any distributed systems. If any element of the DPA is invisible, it results in the failure of programming with GreatFree definitely to implement any systems. On the other hand, if any of the ones encapsulated by the DPA is exposed, it raises the programming efforts and lowers the quality of developed systems obviously. The DPA contains three categories of APIs (Application Programming Interfaces), i.e., the distributed messaging, the distributed modeling and the distributed dispatching. The distributed messaging encloses arbitrary data in the plain object-oriented form such that the data can be transmitted within a networking environment. The distributed modeling specifies the APIs that transform a standalone computing device to the interactive one, i.e., a single physical distributed node, such as a client or a server, in a distributed computing environment. The distributed dispatching is the scaling-up concurrent APIs that leverage computing resources from a distributed node efficiently and effectively. In brief, the distributed modeling constructs an interactive structure to supply distributed computing resources and the distributed dispatching exploits those resources as a well-organized mechanism.

Programming with the DPA only, it is sufficient and necessary to construct the simplest one, the Two-Node/Multiple Node lightweight messaging Client/Server (TNCS/MNCS) distributed system. More important, it is convenient to program with the DP further only to create various Distributed Advanced APIs (DAA), distributed frameworks and even distributed systems. Usually, two categories of DAA, i.e., the distributed clustering and the distributed caching, are required to construct complicated systems. Different from the distributed dispatching, the distributed clustering performs scaling-out concurrency to enlarge the scale of distributed systems. Upon various distributed techniques, the distributed caching aims to retain vast volume data and keep high performance to guarantee high scalability. The DP is sufficient and necessary for the abundant derivations of those high-level programming components to accommodate to the complexity of distributed computing environments, especially the Internet-oriented one. Because of that, the DP is the fundamental programing components to be the primitive distributed computing resources as well as the rudimental distributed computing mechanisms that GreatFree relies on to become a generic distributed programming technique.

The ATM is a distributed threading technique upon the asynchronous message-passing [] methodology. Different from most concurrent programming paradigms [], the ATM threads are visible to developers on the application level, i.e., developers can manipulate those threads upon message-passing without worrying any native characters of threads. Because of the complexity of the Internet-oriented computing environment, it is impossible to predefine an omni-potent concurrency instrument, which not only conceals every detail of threads for rapid programming but also adapts to sophisticated cases. Hence, it is required to allow developers themselves to create, monitor, reuse, and collect threads directly on the application level. Thus, the support of visible threads is necessary for a generic distributed programming paradigm. In addition, to isolate developers from the tedious synchronization workload of threading, the ATM is founded on the basis of the message-passing rather than that of the memory-sharing [] in traditional methodologies []. Using the ATM, developers are able to handle any number of remote threads on arbitrary distributed nodes to process intricate distributed tasks concurrently through message-passing only. In consequence, a single ATM thread is equivalent to a distributed node accomplishing scheduled tasks in a serial way such that developers can program with the ATM threads in the same way as distributed nodes to construct various complicated distributed systems in a higher quality. As a novel distributed concurrency technique, the ATM is another crucial foundation of GreatFree to become a generic distributed programming paradigm.

The DCP is a phenomenon existing natively in the full-fledged distributed code instead of a contrived technique. In accordance with the large amount of distributed programming experiences in various environments, it discovers that the code of heterogeneous distributed systems abides by the homogeneous code-level design patterns, i.e., the DPI. Although it is necessary to derive diverse DAAs to raise the efficiency of programming complicated distributed systems, the types of code-level design patterns do not change with the new proposed APIs. Only the DPI is sufficient to adapt to any scenarios since the patterns for DAA are always the straightforward aggregations of the DPI and no particular ones need to be invented for DAA. Thus, when programming with DAA, the same code structures originated from the DPI are reusable for distinct distributed systems. The phenomenon reveals the truth that the code structures of a distributed system are independent of its distributed natures. Thus, even though there are a variety of mutations in the Internet-oriented computing environment, it is feasible to find a rapid development approach that definitely speeds up distributed systems development. Furthermore, it is practical to generate the code upon domain-specific descriptions only guided by the patterns. In this way, it is attainable to propose a new high-level programming language that encapsulates the current solution of GreatFree. The same as GreatFree, the language is generic for the Internet-oriented computing environment as well. In short, the DCP is the third technical foundation as it discloses the code structure principle for distributed programming.

It is different for application developers and system developers when programming with GreatFree. Application developers keep on modeling upper level applications only on one particular distributed framework, which is implemented with the DP as well as the DAA, without worrying about any distributed APIs and patterns of GreatFree. In contrast, system developers aim to implement new DAAs and new distributed frameworks such that it is required for them to program with the DPA, existing DAAs or distributed frameworks.

In summary, compared with any other distributed programming paradigms [], GreatFree is distinct in the three aspects, i.e., the DP, the ATM and the DCP. The DP represents the primitive components that are sufficient and necessary to program any distributed systems rapidly. The ATM denotes the minimum concurrency component available to developers such that the most fine-grained concurrent and distributed algorithms can be implemented. The DCP stands for the common code structures which prove that it is practical to program any distributed systems in a rapid and homogeneous manner. The current version of GreatFree is a distributed programming environment on the three foundations rather than a new programming language. Although it is imperfect for that, the present solution has already become a novel distributed programming paradigm to raise the efficiency and guarantee the adaptability. It proves that GreatFree is an attainable technology to underpin the first generic distributed programming language for the Internet-oriented computing environment.
![image](https://user-images.githubusercontent.com/10084292/117626015-3c0ce700-b1a9-11eb-9841-99f7315e56ab.png)
