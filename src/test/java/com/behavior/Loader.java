package com.behavior;

/**
 * 测试用例
 */
public class Loader {
    //
    // //自定义扩展log
    // private static Map<String, Class<? extends BaseNode>> extendNodes = new HashMap<String, Class<? extends BaseNode>>() {
    //     {
    //         put("Log", Log.class);
    //         put("DemoCondition", DemoCondition.class);
    //     }
    // };
    //
    //
    // @Test
    // public void loadTree() {
    //     String confJson = Loader.class.getResource("/").getPath() + "demo.json";
    //     BehaviorTree behaviorTree = B3Loader.loadB3Tree(confJson, extendNodes);
    //     Blackboard blackboard = new Blackboard();
    //     B3Status tick = behaviorTree.tick(new Object(), blackboard);
    // }
    //
    // @Test
    // public void loadProject() {
    //     String confJson = Loader.class.getResource("/").getPath() + "project.b3";
    //     BehaviorTreeProject behaviorTreeProject = B3Loader.loadB3Project(confJson, extendNodes);
    //     Blackboard blackboard = new Blackboard();
    //     BehaviorTree behaviorTree = behaviorTreeProject.findBTTreeByTitle("tree1");
    //     behaviorTree.tick(new Object(), blackboard);
    //
    //
    // }
}
