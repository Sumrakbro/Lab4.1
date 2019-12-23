package objects;

public abstract class Bridge extends Objects {
    public Bridge(String title, double weight) {
        super(title, weight);
    }

    public static class Story_Bridge extends Bridge {
        public Side getLeftSide() {
            return leftSide;
        }

        public Side getRightSide() {
            return rightSide;
        }

        protected Side leftSide;
        protected Side rightSide;
        public Story_Bridge(String title,double weight) {
            super(title,weight);
            this.leftSide = new Side("левая сторона");
            this.rightSide = new Side("правая сторона");
        }

        private class Side {
            protected String title;
            Side(String title) {
                this.title = title;
            }
        public String getTitle(){
                return this.title;
        }

            @Override
            public String toString() {
                return this.title;
            }
        }
    }
}
