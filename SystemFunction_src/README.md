# 系统功能实现Demo

- 一些需要具备系统权限+系统签名才能实现的功能  
- 调用SystemLib的app必须申明为系统App+系统签名才能正常使用  
- 测试一些系统功能在App中的实现  

app为调用系统层功能  

SystemLib 为系统层功能的抽象层,如果这个接口是Android5~Android12都有的接口，那么直接由SystemLib实现，如果这个接口每个版本的实现方式不一样，那么就采用对应版本的实现
Android12 保存Android12特有接口    
Android11 保存Android11特有接口  
...  
Android5 保存Android5特有接口  
App依赖->SystemLib(抽象层)->(Android5、Android6、Android7、Android8、Android9、Android10、Android11、Android12)