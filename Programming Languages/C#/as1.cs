using System;

namespace FirstCSOop {

  interface IUFO {
    public void fly();
    public void goToHyperspace();
    public void land();
  }
  
  public class Program {
    
    public static void Main (string[] args) {
      Dog dog = new Dog();
      Cat cat = new Cat();
      Bird bird = new Bird();

      dog.sing();
      dog.wash();
      dog.turnAround();
      dog.move();
      dog.sleep();
      dog.eat();
      
      cat.sing();
      cat.wash();
      cat.turnAround();
      cat.move();
      cat.sleep();
      cat.eat();
      
      bird.sing();
      bird.wash();
      bird.turnAround();
      bird.move();
      bird.sleep();
      bird.eat();

      SmallCraft small = new SmallCraft();
      LargeCraft large = new LargeCraft();
      BossCraft boss = new BossCraft();

      small.fly();
      small.goToHyperspace();
      small.land();

      large.fly();
      large.goToHyperspace();
      large.land();

      boss.fly();
      boss.goToHyperspace();
      boss.land();
    }
  }

  public class SmallCraft : IUFO {
    public void fly() {
      Console.WriteLine("Small Craft is flying");
    }

    public void goToHyperspace() {
      Console.WriteLine("Small Craft is going to Hyperspace");
    }

    public void land() {
      Console.WriteLine("Small Craft is landing");
    }
  }

  public class LargeCraft : IUFO {
    public void fly() {
      Console.WriteLine("Large Craft is flying");
    }

    public void goToHyperspace() {
      Console.WriteLine("Large Craft is going to Hyperspace");
    }

    public void land() {
      Console.WriteLine("Large Craft is landing");
    }
  }

  public class BossCraft : IUFO {
    public void fly() {
      Console.WriteLine("Boss Craft is flying");
    }

    public void goToHyperspace() {
      Console.WriteLine("Boss Craft is going to Hyperspace");
    }

    public void land() {
      Console.WriteLine("Boss Craft is landing");
    }
  }

  public class Animal {
    
    public void move() {
      Console.WriteLine("Moving");
    }

    public void sleep() {
      Console.WriteLine("Sleeping");
    }

    public void eat() {
      Console.WriteLine("Eating");
    }
    
  }

  class Bird : Animal {
    
    public void sing() {
      Console.WriteLine("Bird is singing");
    }

    public void wash() {
      Console.WriteLine("Bird is washing");
    }

    public void turnAround() {
      Console.WriteLine("Bird is turning around");
    }
    
  }

  class Cat : Animal {
    
    public void sing() {
      Console.WriteLine("Cat is singing");
    }

    public void wash() {
      Console.WriteLine("Cat is washing");
    }

    public void turnAround() {
      Console.WriteLine("Cat is turning around");
    }
    
  }

  class Dog : Animal {
    
    public void sing() {
      Console.WriteLine("Dog is singing");
    }

    public void wash() {
      Console.WriteLine("Dog is washing");
    }

    public void turnAround() {
      Console.WriteLine("Dog is turning around");
    }
    
  }
  
}