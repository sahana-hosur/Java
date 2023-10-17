public class Animal {
        private String name;

        public Animal(String name){
            this.name=name;
        }

        public void makesound(){
            System.out.println("The animal makes sound.");
        }

        public String getname(){
            return name;
        }
        public void setname(String name){
            this.name=name;
        }
    }

