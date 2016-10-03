package aStarAlg;

/**
 * Created by admin on 10/2/16.
 */
public class Binary_heap {
    public int size;
    public Cell[] bheap;
    public int position;
    public Binary_heap(int size){
        this.size = size;
        bheap = new Cell[size + 1];
        position = 0;
    }
    /*
    public void createHeap(int[] arrA ){
        if(arrA.length > 0){
            for(int i = 0; i < arrA.length; i++){
                insert(arrA[i]);
            }
        }
    }
    */
    /*
    public void display(){
        for(int i = 1; i < bheap.length; i++){
            System.out.print(" "+bheap[i]);
        }
    }
    */


    public void insert(Cell x){
        if(position - 1 == size){
            Cell[] temp = new Cell[size * 2 +1];
            System.arraycopy(bheap, 0, temp, 0, size + 1);
            size = size * 2;
            bheap = temp;
        }
        if(position == 0){
            bheap[position + 1] = x;
            position = 2;
        }else{
            bheap[position] = x;
            position ++;
            bubbleUp();
        }
    }

    public void bubbleUp(){
        int pos = position -1;
        while( pos > 1 && bheap[pos].fCost < bheap[pos/2].fCost){
            swap(pos, pos/2);
            pos = pos/2;
        }
    }

    public Cell extractMin(){
        Cell min = bheap[1];
        bheap[1] = bheap[position - 1];
        bheap[position - 1] = null;
        position --;
        sinkDown(1);
        return min;
    }

    public void sinkDown(int k){
        int pos = k;
        while(pos * 2 + 1 <= (position - 1)&&(bheap[pos].fCost > bheap[pos * 2].fCost|| bheap[pos].fCost > bheap[pos * 2].fCost)) {
            if (bheap[pos * 2].fCost < bheap[pos * 2 + 1].fCost) {
                swap(pos, pos * 2);
                pos = pos * 2;
            } else {
                swap(pos, pos * 2 + 1);
                pos = pos * 2 + 1;
            }
        }
        if(pos * 2 == position -1){
            if(bheap[pos].fCost > bheap[pos * 2].fCost){
                swap(pos, pos * 2);
            }
        }
    }

    public void swap(int a, int b){
        Cell temp = bheap[a];
        bheap[a] = bheap[b];
        bheap[b] = temp;
    }

    public boolean find(Point c){
        if(position == 0){
            return false;
        }
        for(int i = 1; i < position -1; i++){
            if(c.x == bheap[i].coordinateX && c.y == bheap[i].coordinateY){
                return true;
            }
        }
        return  false;
    }
    /*
    public static void main(String args[]){
        int arrA [] = {3,2,1,7,8,4,10,16,12,3,4,5,8,0,2,1,4};
        System.out.print("Original Array : ");
        for(int i=0;i<arrA.length;i++){
            System.out.print("  " + arrA[i]);
        }
        Binary_heap m = new Binary_heap(4);
        System.out.print("Min-Heap : ");

        //m.display();
        System.out.print("Extract Min :");
        for(int i=0;i<arrA.length;i++){
            System.out.print("  " + m.extractMin());
        }
    }
    */
}
