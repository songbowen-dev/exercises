package songbowen.exercises;

import java.util.LinkedList;

/**
 * 二叉树
 */
public class BinaryTree {
    static class Node {
        int value;
        Node lefChild;
        Node rightChild;

        public Node(int value) {
            this.value = value;
        }
    }

    static void visit(Node node) {
        System.out.print(node.value);
        System.out.print(",");
    }

    /**
     * 前序遍历
     */
    static void preOrderTravel(Node root) {
        if (root != null) {
            visit(root);
            preOrderTravel(root.lefChild);
            preOrderTravel(root.rightChild);
        }
    }

    /**
     * 非递归前序遍历
     */
    static void preOrderNonRecursiveTravel(Node root) {
        if (root == null) {
            return;
        }
        LinkedList<Node> stack = new LinkedList<>();
        stack.push(null);
        Node pointer = root;
        while (pointer != null) {
            visit(pointer);
            if (pointer.rightChild != null) {
                stack.push(pointer.rightChild);
            }
            if (pointer.lefChild != null) {
                pointer = pointer.lefChild;
            } else {
                pointer = stack.pop();
            }
        }
    }

    /**
     * 中序遍历
     */
    static void inOrderTravel(Node root) {
        if (root != null) {
            inOrderTravel(root.lefChild);
            visit(root);
            inOrderTravel(root.rightChild);
        }
    }

    /**
     * 非递归中序遍历
     */
    static void inOrderNonRecursiveTravel(Node root) {
        if (root == null) {
            return;
        }
        LinkedList<Node> stack = new LinkedList<>();
        Node pointer = root;
        while (pointer != null || !stack.isEmpty()) {
            if (pointer != null) {
                if (pointer.lefChild != null) {
                    stack.push(pointer);
                    pointer = pointer.lefChild;
                } else if (pointer.rightChild != null) {
                    visit(pointer);
                    pointer = pointer.rightChild;
                } else {
                    visit(pointer);
                    pointer = null;
                }
            } else {
                pointer = stack.pop();
                visit(pointer);
                if (pointer.rightChild != null) {
                    pointer = pointer.rightChild;
                } else {
                    pointer = null;
                }
            }
        }
    }

    /**
     * 后序遍历
     */
    static void postOrderTravel(Node root) {
        if (root != null) {
            postOrderTravel(root.lefChild);
            postOrderTravel(root.rightChild);
            visit(root);
        }
    }

    static class NonRecursiveTravelElement {
        Node node;
        boolean direction;

        public NonRecursiveTravelElement(Node node, boolean direction) {
            this.node = node;
            this.direction = direction;
        }
    }

    /**
     * 非递归后序遍历
     */
    static void postOrderNonRecursiveTravel(Node root) {
        if (root == null) {
            return;
        }
        LinkedList<NonRecursiveTravelElement> stack = new LinkedList<>();
        Node pointer = root;
        while (pointer != null || !stack.isEmpty()) {
            if (pointer != null) {
                if (pointer.lefChild != null) {
                    stack.push(new NonRecursiveTravelElement(pointer, false));
                    pointer = pointer.lefChild;
                } else if (pointer.rightChild != null) {
                    stack.push(new NonRecursiveTravelElement(pointer, true));
                    pointer = pointer.rightChild;
                } else {
                    visit(pointer);
                    pointer = null;
                }
            } else {
                NonRecursiveTravelElement travelElement = stack.pop();
                pointer = travelElement.node;
                if (!travelElement.direction) {
                    if (pointer.rightChild != null) {
                        stack.push(new NonRecursiveTravelElement(pointer, true));
                        pointer = pointer.rightChild;
                    } else {
                        visit(pointer);
                        pointer = null;
                    }
                } else {
                    visit(pointer);
                    pointer = null;
                }
            }
        }
    }

    /**
     * 广度优先遍历
     */
    static void depthOrderTravel(Node root) {
        if (root == null) {
            return;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node head = queue.pop();
            visit(head);
            if (head.lefChild != null) {
                queue.offer(head.lefChild);
            }
            if (head.rightChild != null) {
                queue.offer(head.rightChild);
            }
        }

    }


    static Node genTestNode() {
        Node root = new Node(0);
        root.lefChild = new Node(1);
        root.lefChild.lefChild = new Node(3);
        root.lefChild.rightChild = new Node(4);
        root.lefChild.lefChild.lefChild = new Node(9);
        root.lefChild.lefChild.lefChild.rightChild = new Node(15);
        root.lefChild.lefChild.lefChild.rightChild.lefChild = new Node(16);

        root.rightChild = new Node(2);
        root.rightChild.lefChild = new Node(6);
        return root;
    }

    /**
     * 查找二叉树子节点的最近共同父节点
     */
    static Node findFather(Node root, int p, int q) {
        if (root == null) {
            return null;
        }
        if (root.value == p || root.value == q) {
            return root;
        }
        Node leftResult = findFather(root.lefChild, p, q);
        Node rightResult = findFather(root.rightChild, p, q);
        if (leftResult != null && rightResult != null) {
            return root;
        }
        return leftResult != null ? leftResult : rightResult;

    }


    public static void main(String[] args) {
        Node root = genTestNode();
        Node father = findFather(root, 9, 4);
        System.out.println(father.value);
    }


}
