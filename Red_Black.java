import java.util.LinkedList;

public class Red_Black
{
    private int count;
    private Node root;
    private final boolean BLACK = true;
    private final boolean RED = false;
    private Node nil;

    public Red_Black() 
    {
        count = 0;
        root = null;
        nil = new Node (-1, "");
        nil.right = nil;
        nil.left = nil;
    }

    private class Node 
    {
        private Integer key;
        private String value;
        private Node father;
        private Node left;
        private Node right;
        private boolean cor;
        public Node(Integer key ,String value) 
        {
            father = nil;
            left = nil;
            right = nil;
            this.value = value;
            this.cor = RED;
        }
    }

    //Complexidade : O(c)
    public boolean isEmpty() {
        return (root == null);
    }

    public String get(String key)
    {
        Integer chave = criaChave(key);
        //Node n = 
        searchNodeRef(chave, root);
        return null;
    }
    
    //Complexidade : O(c)
    public int size() {
        return count;
    }
    
    //Complexidade : O(c)
    public String getRoot(){
        if (isEmpty()) {
            return null ;
        }
        return root.value;
    }
    
    //Complexidade : O(n log n)
    public boolean contains(Integer key) {
        Node nAux = searchNodeRef(key,root);
        return (nAux != null);
    }
    
    //Complexidade : O(c)
    public boolean isRoot(String element) {
        if (root != null) {
            if (root.value.equals(element)) {
                return true;
            }
        }
        return false;
    }    
    
    //Complexidade : O(c)
    public void clear() 
    {
        count = 0;
        root = null;      
    }
    
    
    //Complexidade : O(n log n)
    private Node searchNodeRef(Integer key, Node target) 
    {
      
        if (target == nil ||target.key == null) {
            return null;
        }

        if (key == target.key) 
        {
            return target;
        } 
        else if (key > target.key) 
        {
            
            return searchNodeRef(key, target.left);
        } 
        else 
        {
            return searchNodeRef(key, target.right);
        }
       
    }      

    //Complexidade : O(n log n)
    private int countNodes(Node n) {
        if (n == null) {
            return 0;
        } else {
            return 1 + countNodes(n.left) + countNodes(n.right);
        }
    }  

    //Complexidade : O(?)
    public LinkedList<String> positionsPre() {
        LinkedList<String> res = new LinkedList<>();
        positionsPreAux(root, res);
        return res;
    }
    private void positionsPreAux(Node n, LinkedList<String> res) {
        if (n != nil) {
            res.add(n.value + ".\n"); //Visita o nodo
            positionsPreAux(n.left, res); //Visita a subarvore esquerda
            positionsPreAux(n.right, res); //Visita a subarvore direita
        }
    }

    //Complexidade : O(?)
    public LinkedList<String> positionsCentral() {
        LinkedList<String> res = new LinkedList<>();
        positionsCentralAux(root, res);
        return res;
    }
    
    private void positionsCentralAux(Node n, LinkedList<String> res) {
        if (n != nil) {
            positionsCentralAux(n.left, res); //Visita a subarvore esquerda
            res.add(n.value + ".\n"); //Visita o nodo
            positionsCentralAux(n.right, res); //Visita a subarvore direita
        }
    }

    public LinkedList<String> positionsPost()
    {
        LinkedList<String> res = new LinkedList<>();
        positionsPostAux(root, res);
        return res;
    }
    
    private void positionsPostAux(Node n, LinkedList<String> res) {
        if (n != nil) {
            positionsCentralAux(n.left, res); //Visita a subarvore esquerda
            positionsCentralAux(n.right, res); //Visita a subarvore direita
            res.add(n.value + ".\n"); //Visita o nodo
        }
    }

    //Complexidade : O(c)
    public String getfather(Node n){return n.father.value;}

    public Integer criaChave(String key)throws IllegalArgumentException
    {
        if(key.length() < 8)throw new IllegalArgumentException("Formato invalido");
        StringBuilder sb = new StringBuilder(
            key.length());
            for(int i = 0; i < key.length(); i++)
            {
                char c = key.charAt(i);
                if(c > 47 && c < 58)
                {
                    sb.append(c);
                }
            }
        String aux = sb.toString();
        if(aux.length() != 8)throw new IllegalArgumentException("Formato invalido");

        String dia = String.valueOf(aux.charAt(0)) + String.valueOf(aux.charAt(1));
        String mes = String.valueOf(aux.charAt(2)) + String.valueOf(aux.charAt(3));
        String ano = String.valueOf(aux.charAt(4)) + String.valueOf(aux.charAt(5))
                + String.valueOf(aux.charAt(6)) + String.valueOf(aux.charAt(7));
        
        if(Integer.parseInt(dia) > 31 || Integer.parseInt(mes) > 12){throw new IllegalArgumentException("Data invalida");}
        if(Integer.parseInt(dia) < 1 || Integer.parseInt(mes) < 1){throw new IllegalArgumentException("Data invalida");}

        if(Integer.parseInt(mes) == 4 || Integer.parseInt(mes) == 6 || Integer.parseInt(mes) == 9 || Integer.parseInt(mes) == 11)
        {
            if(Integer.parseInt(dia) > 30)throw new IllegalArgumentException("Data invalida");
        }

        if(Integer.parseInt(mes) == 2)
        {
            if(Integer.parseInt(dia) == 29)
            {
                if((Integer.parseInt(ano) % 400 != 0) && ((Integer.parseInt(ano) % 4 != 0) || (Integer.parseInt(ano) % 100 == 0))){
                    throw new IllegalArgumentException("Data invalida");
                }
            }   
        }

        return Integer.parseInt(dia+mes+ano);
    
    }
    
    
    //Complexidade : O(?)
    public void add(String key, String valor) 
    {
        
        
        Integer chave = criaChave(key);
        if(root == null)
        {
            root = new Node(chave, key + ":" + valor);
            root.cor = BLACK;
            return;
        }
        if(!contains(chave))
        {
        Node novo = new Node(chave, key + ":" + valor);
        boolean test = false;
        add(root, nil, novo,chave, test, valor);
        count++;
        //fixTree(novo);
    
        }
        
    }

    private void add(Node n, Node pai, Node novo,Integer chave, boolean test, String value)
    {
        
        if(test)return;
        if(n == nil)
        {
            System.out.println(pai.key + n.key);
            if(pai.key > chave)
            {
                pai.left = novo;
            }
            else
            {
                pai.right = novo;
            }
            test = !test;
            return;
        }
        else
        {
            
            
            if(n.key > chave)
            {
                add(n.left, n, novo,chave, test, value);
            }
            else
            {
                add(n.right, n, n,chave, test, value);
            }

        }

    }

    private void insert(Node node) {
        Node temp = root;
        if (root == nil) {
            root = node;
            node.cor = BLACK;
            node.father = nil;
        } else {
            node.cor = RED;
            while (true) {
                if (node.key < temp.key) {
                    if (temp.left == nil) {
                        temp.left = node;
                        node.father = temp;
                        break;
                    } else {
                        temp = temp.left;
                    }
                } else if (node.key >= temp.key) {
                    if (temp.right == nil) {
                        temp.right = node;
                        node.father = temp;
                        break;
                    } else {
                        temp = temp.right;
                    }
                }
            }
            fixTree(node);
        }
    }
    
    private void fixTree(Node node) {
        while (node.father.cor == RED) {
            Node uncle = nil;
          
            if (node.father == node.father.father.left) {
                uncle = node.father.father.right;

                if (uncle != nil && uncle.cor == RED) {
                    node.father.cor = BLACK;
                    uncle.cor = BLACK;
                    node.father.father.cor = RED;
                    node = node.father.father;
                    continue;
                } 
                if (node == node.father.right) {
                    //Double rotation needed
                    node = node.father;
                    rotateLeft(node);
                } 
                node.father.cor = BLACK;
                node.father.father.cor = RED;
                //if the "else if" code hasn't executed, this
                //is a case where we only need a single rotation 
                rotateRight(node.father.father);
            } else {
                uncle = node.father.father.left;
                 if (uncle != nil && uncle.cor == RED) {
                    node.father.cor = BLACK;
                    uncle.cor = BLACK;
                    node.father.father.cor = RED;
                    node = node.father.father;
                    continue;
                }
                if (node == node.father.left) {
                    //Double rotation needed
                    node = node.father;
                    rotateRight(node);
                }
                node.father.cor = BLACK;
                node.father.father.cor = RED;
                //if the "else if" code hasn't executed, this
                //is a case where we only need a single rotation
                rotateLeft(node.father.father);
            }
        }
        root.cor = BLACK;
    }
    
    void rotateLeft(Node node) {
        if (node.father != nil) {
            if (node == node.father.left) {
                node.father.left = node.right;
            } else {
                node.father.right = node.right;
            }
            node.right.father = node.father;
            node.father = node.right;
            if (node.right.left != nil) {
                node.right.left.father = node;
            }
            node.right = node.right.left;
            node.father.left = node;
        } else {//Need to rotate root
            Node right = root.right;
            root.right = right.left;
            right.left.father = root;
            root.father = right;
            right.left = root;
            right.father = nil;
            root = right;
        }
    }
    
    void rotateRight(Node node) {
        if (node.father != nil) {
            if (node == node.father.left) {
                node.father.left = node.left;
            } else {
                node.father.right = node.left;
            }

            node.left.father = node.father;
            node.father = node.left;
            if (node.left.right != nil) {
                node.left.right.father = node;
            }
            node.left = node.left.right;
            node.father.right = node;
        } else {//Need to rotate root
            Node left = root.left;
            root.left = root.left.right;
            left.right.father = root;
            root.father = left;
            left.right = root;
            left.father = nil;
            root = left;
        }
    }
    
    //Deletion Code .
    
    //This operation doesn't care about the new Node's connections
    //with previous node's left and right. The caller has to take care
    //of that.
    void transplant(Node target, Node with){ 
        if(target.father == nil){
            root = with;
        }else if(target == target.father.left){
            target.father.left = with;
        }else
            target.father.right = with;
        with.father = target.father;
  }
    
    public boolean delete(String chave){
        Node aux = searchNodeRef(criaChave(chave), root);
        if(aux == null)return false;
        Node x;
        Node y = aux; // temporary reference y
        boolean y_original_cor = y.cor;
        
        if(aux.left == nil){
            x = aux.right;  
            transplant(aux, aux.right);  
        }else if(aux.right == nil){
            x = aux.left;
            transplant(aux, aux.left); 
        }else{
            y = treeMinimum(aux.right);
            y_original_cor = y.cor;
            x = y.right;
            if(y.father == aux)
                x.father = y;
            else{
                transplant(y, y.right);
                y.right = aux.right;
                y.right.father = y;
            }
            transplant(aux, y);
            y.left = aux.left;
            y.left.father = y;
            y.cor = aux.cor; 
        }
        if(y_original_cor)
            deleteFixup(x);  
        count--;
        return true;
    }
    
    void deleteFixup(Node x){
        while(x!=root && x.cor == BLACK){ 
            if(x == x.father.left){
                Node w = x.father.right;
                if(w.cor == RED){
                    w.cor = BLACK;
                    x.father.cor = RED;
                    rotateLeft(x.father);
                    w = x.father.right;
                }
                if(w.left.cor == BLACK && w.right.cor == BLACK){
                    w.cor = RED;
                    x = x.father;
                    continue;
                }
                else if(w.right.cor == BLACK){
                    w.left.cor = BLACK;
                    w.cor = RED;
                    rotateRight(w);
                    w = x.father.right;
                }
                if(w.right.cor == RED){
                    w.cor = x.father.cor;
                    x.father.cor = BLACK;
                    w.right.cor = BLACK;
                    rotateLeft(x.father);
                    x = root;
                }
            }else{
                Node w = x.father.left;
                if(w.cor == RED){
                    w.cor = BLACK;
                    x.father.cor = RED;
                    rotateRight(x.father);
                    w = x.father.left;
                }
                if(w.right.cor == BLACK && w.left.cor == BLACK){
                    w.cor = RED;
                    x = x.father;
                    continue;
                }
                else if(w.left.cor == BLACK){
                    w.right.cor = BLACK;
                    w.cor = RED;
                    rotateLeft(w);
                    w = x.father.left;
                }
                if(w.left.cor == RED){
                    w.cor = x.father.cor;
                    x.father.cor = BLACK;
                    w.left.cor = BLACK;
                    rotateRight(x.father);
                    x = root;
                }
            }
        }
        x.cor = BLACK; 
    }
    
    Node treeMinimum(Node subTreeRoot){
        while(subTreeRoot.left!=nil){
            subTreeRoot = subTreeRoot.left;
        }
        return subTreeRoot;
    }
    
}