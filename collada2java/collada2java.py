#!/bin/env python
# -*- coding: utf-8 -*-
from __future__ import print_function
import sys
import os.path
import time
import collada
from StringIO import StringIO

class ShapeTypeList(object):
	
	def __init__(self, primitivelist):
		super(ShapeTypeList, self).__init__()
		self.primitivelist = primitivelist
		self.codebits = []
		self.prevfacetype = ""

		self.parseprimitivelist()

	def glPrimativeType(self):
		if isinstance(self.primitivelist, collada.polylist.Polylist):
			return "GL11.GL_POLYGON"
		if isinstance(self.primitivelist, collada.triangleset.TriangleSet):
			return "GL11.GL_TRIANGLES"

		raise Exception('Unknown type: {0}'.format(type(self.primitivelist).__name__))

	def parseprimitivelist(self):
		self.verticies = []
		
		if(len(self.primitivelist) == 0):
			return

		for i in range(len(self.primitivelist)):
			primitive = self.primitivelist[i]


			if(self.glPrimativeType() == "GL11.GL_POLYGON" and len(primitive.vertices) == 3):
				facetype = "GL11.GL_TRIANGLES"
			elif(self.glPrimativeType() == "GL11.GL_POLYGON" and len(primitive.vertices) == 4):
				facetype = "GL11.GL_QUADS"
			else:
				facetype = self.glPrimativeType()


			if(self.prevfacetype != "" and self.prevfacetype != facetype):
				self.codebits.append('}\nGL11.glEnd();\n')

			if(self.prevfacetype != facetype):
				self.codebits.append('\nGL11.glBegin(')
				self.codebits.append(facetype)
				self.codebits.append(');\n{\n')
				self.prevfacetype = facetype

			# normals are the same for the entire face
			normal = primitive.normals[0]
			# self.codebits.append('    (new Normal({0}f,{1}f,{2}f)).submit();\n'.format(normal[0], normal[1], normal[2]))
			self.codebits.append('    GL11.glNormal3f({0}f,{1}f,{2}f);\n'.format(normal[0], normal[1], normal[2]))

			for j in range(len(primitive.vertices)):
				vertex = primitive.vertices[j]
				if(len(primitive.texcoords) > 0):
					texcoords = primitive.texcoords[0][j]
					self.codebits.append('    GL11.glTexCoord2f({0}f,{1}f);\n'.format(texcoords[0], texcoords[1])) #ignore third value, it's never needed
				# self.codebits.append('    (new Vertex({0}f,{1}f,{2}f)).submit();\n'.format(vertex[0], vertex[1], vertex[2]))
				self.codebits.append('    GL11.glVertex3f({0}f,{1}f,{2}f);\n'.format(vertex[0], vertex[1], vertex[2]))

		self.codebits.append('}\nGL11.glEnd();\n')


	def getCode(self):
		return ''.join(self.codebits)
		

if __name__ == '__main__':

	if(len(sys.argv) < 2):
		raise Exception('No file specified')

	dae_file = open(sys.argv[1])
	dae_data = dae_file.read()

	model = collada.Collada(StringIO(dae_data))

	for geometry in model.geometries:
		# outfile = open(geometry.id+".java", "wb")
		for listtype in geometry.primitives:
			if isinstance(listtype, collada.polylist.Polylist) or isinstance(listtype, collada.triangleset.TriangleSet):
				stl = ShapeTypeList(listtype)

				headerString = ("/**\n"
								"* Source file: {filename}\n"
								"* Date: {date}\n"
								"* Generated with collada2java by Antoine Le\n"
								"* http://antoine.me.uk/codedumps/collada2java/\n"
								"*/").format(filename = sys.argv[1], date=time.ctime(os.path.getmtime(sys.argv[1])))

				print(headerString)

				print(stl.getCode())
