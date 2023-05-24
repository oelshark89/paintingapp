/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.awt.Graphics;
import java.awt.Point;

public interface Moveable {

    public void setDraggingPoint(Point p);

    public Point getDraggingPoint();

    public boolean contains(Point p);

    public void moveTo(Point p);
    public void resize(Point p);

}
