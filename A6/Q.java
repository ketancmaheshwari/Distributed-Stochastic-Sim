public class Q
{
   private class QueueNode
   {
      Object item;
      QueueNode link;
   }

   private QueueNode front;
   private QueueNode rear;
   private int itemCount;
   
   public Q()
   {
      itemCount = 0;
   }


   public synchronized final boolean isEmpty()
   {
      return (itemCount == 0);
   }

 
   public synchronized final void insert(Object newItem)
   {
      QueueNode temp = new QueueNode();

      temp.item = newItem;
      temp.link = null;
      if(rear == null)
      {
         front = temp;
         rear = temp;
      }
      else
      {
         rear.link = temp;
         rear = temp;
      }
      itemCount++;
   }

   public synchronized final Object remove()
   {
      if(itemCount == 0)
         return null;

      Object temp = front.item;
      front = front.link;

      if(front == null)
         rear = null;

      itemCount--;
      return temp;
   }


   public synchronized final Object[] toArray()
   {
      Object temp[] = new Object[itemCount];
      QueueNode node = front;

      for (int i = 0; i < itemCount; i++)
      {
         temp[i] = node.item;
         node = node.link;
      }

      return temp;
   }
   public synchronized final int getCurrentSize(){
		return itemCount;
   }		

}
