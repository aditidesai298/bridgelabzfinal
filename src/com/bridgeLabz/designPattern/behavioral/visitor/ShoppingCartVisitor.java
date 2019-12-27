package com.bridgeLabz.designPattern.behavioral.visitor;

public interface ShoppingCartVisitor {

	int visit(Book book);
	int visit(Fruit fruit);
}