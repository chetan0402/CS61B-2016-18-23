public class ArrayDeque<Generic> {
    private int prev;
    private int next;
    private int size;
    private Generic[] array;

    public ArrayDeque(){
        size=0;
        prev=4;
        next=5;
        array=(Generic[]) new Object[8];
    }

    public void addFirst(Generic item){
        if(prev==next) {
            upgrade();
        }

        array[prev]=item;
        size=size+1;

        if(prev==0){
            prev=array.length-1;
        }else{
            prev=prev-1;
        }
    }

    public void addLast(Generic item){
        if(prev==next) {
            upgrade();
        }

        array[next]=item;
        size=size+1;

        if(next==array.length-1){
            next=0;
        }else{
            next=next+1;
        }
    }

    public boolean isEmpty(){
        return (size==0);
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        StringBuilder to_print=new StringBuilder();
        for (int i = 1; i < size+1; i++) {
            to_print.append(" ").append(get(i));
        }
        System.out.println(to_print);
    }

    public Generic removeFirst(){
        Generic to_return;
        if(prev==array.length-1){
            to_return=array[0];
            array[0]=null;
            prev=0;
        }else{
            to_return=array[prev+1];
            array[prev+1]=null;
            prev=prev+1;
        }
        size=size-1;
        clean();
        return to_return;
    }

    public Generic removeLast(){
        Generic to_return;
        if(next==0){
            to_return=array[array.length-1];
            array[array.length-1]=null;
            next= array.length-1;
        }else{
            to_return=array[next-1];
            array[next-1]=null;
            next=next-1;
        }
        size=size-1;
        clean();
        return to_return;
    }

    public void clean(){
        if((double) size / array.length < 0.25){
            Generic[] new_array=(Generic[]) new Object[array.length/2];
            if(prev<next){
                System.arraycopy(array,prev+1,new_array,1,size);
            }else{
                System.arraycopy(array,prev+1,new_array,1,array.length-prev-1);
                System.arraycopy(array,0,new_array,array.length-prev,next);
            }
            prev=0;
            next=size+1;
            array=new_array;
        }
    }

    public void upgrade(){
        Generic[] new_array=(Generic[]) new Object[2* array.length];
        if(prev==0){
            System.arraycopy(array,prev+1,new_array,1,array.length);
        }else{
            System.arraycopy(array,prev+1,new_array,1,array.length-prev-1);
            System.arraycopy(array,0,new_array,array.length-prev,prev+1);
        }
        prev=0;
        next=array.length;
        array=new_array;
    }

    public Generic get(int index){
        if(prev+index < array.length){
            return array[prev+index];
        }else{
            index=index-(array.length-prev);
            return array[index];
        }
    }

    public Generic[] rawArray(){
        return array;
    }
}
