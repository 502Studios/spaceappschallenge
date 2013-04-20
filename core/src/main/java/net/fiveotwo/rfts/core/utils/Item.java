package net.fiveotwo.rfts.core.utils;
/*
 * used by the entities, allows for multiple uses and items
 */
public abstract class Item {
	//0=bare fists, 1=sword. 2=magic rod, 3=hammer, 4=bow, 5=hp
	int type;
	int amount;
	Entity father;
	public Item(Entity ent, int amount){
		father=ent;
		setAmount(amount);
	}
	public abstract void use();
	public void setType(int t){this.type=t;}
	public void setAmount(int t){this.amount=t;}
	public int getType(){return this.type;}
	public int getAmount(){return this.amount;}
	public Entity getFather(){return this.father;}
}
